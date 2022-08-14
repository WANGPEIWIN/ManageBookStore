package com.bookMall.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class springBootStart implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("　 ∧＿∧\n" +
                "（｡･ω･｡)つ━☆・*。\n" +
                "⊂　　 ノ 　　　・゜+.\n" +
                "　しーＪ　　　°。+ *´¨)\n" +
                "　　　 　　.· ´¸.·*´¨) ¸.·*¨)\n" +
                "　　　　　　　 　(¸.·´ (¸.·’* 启动成功!");
    }
}
