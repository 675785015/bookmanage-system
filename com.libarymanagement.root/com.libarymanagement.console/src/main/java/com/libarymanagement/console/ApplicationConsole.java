package com.libarymanagement.console;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Lee on 2018/2/8.
 */
@SpringBootApplication
@ComponentScan("com.libarymanagement.core,com.libarymanagement.console")
public class ApplicationConsole {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConsole.class,args);
    }
}
