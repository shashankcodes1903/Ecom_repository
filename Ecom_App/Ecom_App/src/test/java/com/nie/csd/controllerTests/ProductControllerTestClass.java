package com.nie.csd.controllerTests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nie.csd.Controller.ProductController;

@SpringBootTest
public class ProductControllerTestClass {
    @Autowired
    ProductController controller;

    public void testSayHello() {
        // Test logic here
        String result=controller.sayHello();
        String expected="HELLO";
        assert(result.equals(expected));
    }

}
