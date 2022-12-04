package org.shuijing.guiguijizhang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class GuiGuijizhangSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuiGuijizhangSpringApplication.class, args);
        System.out.println("启动成功！");
    }

}
