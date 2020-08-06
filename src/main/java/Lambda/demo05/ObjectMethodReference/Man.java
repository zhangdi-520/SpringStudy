package Lambda.demo05.ObjectMethodReference;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-24 10:14
 **/
public class Man extends Human {

    @Override
    public void sayHello() {
        System.out.println("hello 我是Man");
    }

    public void method(Greetable g){
        g.greet();
    }
    public void show(){
        method(()->{
            Human h = new Human();
            h.sayHello();
        });

        method(super::sayHello);
    }

    public static void main(String[] args) {
        new Man().show();
    }
}
