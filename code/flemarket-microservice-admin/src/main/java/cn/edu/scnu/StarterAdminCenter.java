package cn.edu.scnu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("cn.edu.scnu.admin.mapper")
@EnableEurekaClient

public class StarterAdminCenter {
	
	public static void main(String[] args) {
		SpringApplication.run(StarterAdminCenter.class, args);
	}

}
