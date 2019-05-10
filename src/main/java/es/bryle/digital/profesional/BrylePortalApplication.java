package es.bryle.digital.profesional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BrylePortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BrylePortalApplication.class, args);
	}

}
