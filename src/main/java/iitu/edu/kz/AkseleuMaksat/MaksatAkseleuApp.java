package iitu.edu.kz.AkseleuMaksat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "iitu.edu.kz.AkseleuMaksat.Repository")
@EntityScan(basePackages = "iitu.edu.kz.AkseleuMaksat.Entity")
public class MaksatAkseleuApp {

	public static void main(String[] args) {
		SpringApplication.run(MaksatAkseleuApp.class, args);
	}

}
