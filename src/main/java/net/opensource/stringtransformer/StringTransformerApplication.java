package net.opensource.stringtransformer;

import io.mongock.runner.springboot.EnableMongock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableMongock
public class StringTransformerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StringTransformerApplication.class, args);
    }
}