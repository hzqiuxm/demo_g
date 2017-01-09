package com.example;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzqiuxm on 2016/12/21 0021.
 */
@RestController
@SpringBootApplication
public class Ch001 {

    @Value("${book.author}")
    private String bookAuthor;
    @Value("${book.name}")
    private String bookName;

    @RequestMapping("/qiuxm")
    String index() {

        return "book name is " + bookName + " and book author is " + bookAuthor;
    }

    public static void main(String[] args) {
        SpringApplication.run(Ch001.class,args);
    }


}
