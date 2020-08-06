package com.example.springboot.entityTest;


import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.boot.registry.classloading.spi.ClassLoadingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.UUIDGenerationStrategy;
import org.hibernate.id.uuid.StandardRandomStrategy;
import org.hibernate.internal.CoreLogging;
import org.hibernate.internal.CoreMessageLogger;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.PassThroughTransformer;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToBytesTransformer;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ToStringTransformer;
import org.hibernate.type.descriptor.java.UUIDTypeDescriptor.ValueTransformer;

@Slf4j
public class AssignedUUIDGenerator implements IdentifierGenerator, Configurable {
    private static final CoreMessageLogger LOG = CoreLogging.messageLogger(AssignedUUIDGenerator.class);
    private UUIDGenerationStrategy strategy;
    private ValueTransformer valueTransformer;

    private String entityName;

    public AssignedUUIDGenerator() {
    }

    public static AssignedUUIDGenerator buildSessionFactoryUniqueIdentifierGenerator() {
        AssignedUUIDGenerator generator = new AssignedUUIDGenerator();
        generator.strategy = StandardRandomStrategy.INSTANCE;
        generator.valueTransformer = ToStringTransformer.INSTANCE;
        return generator;
    }

    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        this.strategy = (UUIDGenerationStrategy)params.get("uuid_gen_strategy");
        if(this.strategy == null) {
            String strategyClassName = params.getProperty("uuid_gen_strategy_class");
            if(strategyClassName != null) {
                try {
                    ClassLoaderService ignore = (ClassLoaderService)serviceRegistry.getService(ClassLoaderService.class);
                    Class strategyClass = ignore.classForName(strategyClassName);

                    try {
                        this.strategy = (UUIDGenerationStrategy)strategyClass.newInstance();
                    } catch (Exception var8) {
                        LOG.unableToInstantiateUuidGenerationStrategy(var8);
                    }
                } catch (ClassLoadingException var9) {
                    LOG.unableToLocateUuidGenerationStrategy(strategyClassName);
                }
            }
        }

        if(this.strategy == null) {
            this.strategy = StandardRandomStrategy.INSTANCE;
        }

        if(UUID.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = PassThroughTransformer.INSTANCE;
        } else if(String.class.isAssignableFrom(type.getReturnedClass())) {
            this.valueTransformer = ToStringTransformer.INSTANCE;
        } else {
            if(!byte[].class.isAssignableFrom(type.getReturnedClass())) {
                throw new HibernateException("Unanticipated return type [" + type.getReturnedClass().getName() + "] for UUID conversion");
            }

            this.valueTransformer = ToBytesTransformer.INSTANCE;
        }

        this.entityName = params.getProperty("entity_name");
        if(this.entityName == null) {
            throw new MappingException("no entity name");
        }
    }

    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Serializable id = session.getEntityPersister(this.entityName, object).getIdentifier(object, session);
        if(id == null) {
            id = this.valueTransformer.transform(this.strategy.generateUUID(session));

            return StringUtils.replaceAll((String) id, "-", "");
        } else {
            return id;
        }
    }
}