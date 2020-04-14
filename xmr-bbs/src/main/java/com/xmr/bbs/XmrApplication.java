package com.xmr.bbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.xmr.bbs.dao")
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling   // 1.开启定时任务
@ServletComponentScan
public class XmrApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmrApplication.class, args);
	}
}
