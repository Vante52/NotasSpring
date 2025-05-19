package co.edu.Javeriana;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class JaverianaApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(JaverianaApplication.class, args);
		Object dataSource = context.getBean("dataSource");
		System.out.println(dataSource);

	}

}
