package SGP_Backend.SGP_Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SgpBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgpBackendApplication.class, args);
	}


}

/*
controller: Para controladores REST.
service: Para lógica de negócios.
repository: Para interações com o banco de dados.
model/entity: Para classes de domínio e entidades JPA.
dto: Para objetos de transferência de dados.
config: Para configurações do projeto (ex: segurança, CORS).
exception: Para classes de exceção personalizadas.
util: Para classes utilitárias. */
