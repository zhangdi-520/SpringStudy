package Lambda.demo05.ObjectMethodReference;

import org.springframework.cache.annotation.Cacheable;

import java.util.concurrent.Callable;

/**
 * @version V1.0
 * @program: Spring
 * @description: 通过对象名引用成员方法，前提对象和成员方法都存在
 * @author: Mr.Zhang
 * @create: 2020-07-24 09:51
 **/
public class Demo01ObjectMethodReference {

    public static void printString(Printable p){
        p.print("Hello");
    }

    public static int method(int number, Calcable c){
        return c.calsAbs(number);
    }

    public static void main(String[] args) {
//        printString((s)->{
//            MethodRerObject obj = new MethodRerObject();
//            obj.printUpperCaseString(s);
//        });
//
//        MethodRerObject obj = new MethodRerObject();
//        printString(obj::printUpperCaseString);


        //通过类名引用Math类的静态成员方法
        int method = method(-10, Math::abs);
        System.out.println(method);
    }
}
