package lt.ca.javau11.gr.carservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "lt.ca.javau11.gr.carservice")
public class CarserviceApplication {

	public static void main(String[] args) {
				SpringApplication.run(CarserviceApplication.class, args);
	}

}
