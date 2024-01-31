package week6.java.cogip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class CogipApplication {

	public static void main(String[] args) {
		SpringApplication.run(CogipApplication.class, args);
	}

}
