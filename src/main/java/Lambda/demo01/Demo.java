package Lambda.demo01;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-23 11:56
 **/
public class Demo {

    public static void show(MyFunctionInterface myFunctionInterface){
        myFunctionInterface.method();
    }

    public static void main(String[] args) {

        show(new MyFunctionfaceImpl());

        show(new MyFunctionInterface() {
            @Override
            public void method() {
                System.out.println("使用匿名内部类重写该接口中的方法");
            }
        });

        show(()->System.out.println("使用Lambda表达式重写接口的抽象方法"));
    }
}
