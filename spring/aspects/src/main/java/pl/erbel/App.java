package pl.erbel;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ctx.start();
        System.out.println(Arrays.toString(ctx.getBeanDefinitionNames()));
        ctx.close();
    }
}
