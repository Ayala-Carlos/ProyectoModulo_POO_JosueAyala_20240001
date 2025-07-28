package Proyecto_Modulo_JosueAyala.JosueAyala_20240001;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JosueAyala20240001Application {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
		dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(),entry.getValue()));
		SpringApplication.run(JosueAyala20240001Application.class, args);
	}

}
