package com.ricettario.Ricettario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RicettarioApplication {
	public static void main(String[] args) {
		/*ObjectMapper mapper = new ObjectMapper();
		UserRicettarioFormDTO receipeDTO = new UserRicettarioFormDTO();
		try {
			mapper.writeValue(new File("UserRicettarioFormDTO.json"), receipeDTO);
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(receipeDTO);
			System.out.println(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		SpringApplication.run(RicettarioApplication.class, args);
	}
}
