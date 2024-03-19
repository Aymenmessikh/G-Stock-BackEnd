package project.sgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SgsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SgsApplication.class, args);
    }

}
