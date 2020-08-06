package Lambda.demo01;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-23 11:51
 **/
/*
* @FunctionalInterface检验一个接口是否是函数式接口
* 函数式接口为有且只有一个抽象方法的接口
* */
@FunctionalInterface
public interface MyFunctionInterface {
    public abstract void method();
}
