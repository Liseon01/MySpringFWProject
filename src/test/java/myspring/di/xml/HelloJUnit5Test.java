package myspring.di.xml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach; // ✅ JUnit 5 import
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloJUnit5Test {
    ApplicationContext context;

    @BeforeEach
    void createContainer() {
        context = new GenericXmlApplicationContext("classpath:spring-beans.xml");
    }

    @Test
    void helloBean() {
        // container 객체 생성
        Hello helloById = (Hello) context.getBean("hello");
        Hello helloByType = context.getBean("hello", Hello.class);

        System.out.println(helloById == helloByType);
        assertEquals("Hello 최성윤", helloByType.sayHello());

        helloByType.print();

        Printer printer = context.getBean("stringPrinter", Printer.class);
        assertEquals("Hello 최성윤", printer.toString());
    }
}
