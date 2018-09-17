package microservices.tellme.notemodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class NotemoduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotemoduleApplication.class, args);
	}
}
