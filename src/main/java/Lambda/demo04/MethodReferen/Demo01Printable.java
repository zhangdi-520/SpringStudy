package Lambda.demo04.MethodReferen;

/**
 * @version V1.0
 * @program: Spring
 * @description: TODO
 * @author: Mr.Zhang
 * @create: 2020-07-24 09:34
 **/
public class Demo01Printable {

    public static void printString(Printable p){
        p.print("HelloWorld");
    }

    public static void main(String[] args) {
        printString((s)->{
            System.out.println(s);
        });

        //传递的参数一定是方法引用中那个方法可以接收的参数
        //System.out::println方法引用
        printString(System.out::println);
    }
}
