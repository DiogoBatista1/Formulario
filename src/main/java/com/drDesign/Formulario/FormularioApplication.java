package com.drDesign.Formulario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.drDesign.Formulario.config.CorsConfig;

@SpringBootApplication
@Import(CorsConfig.class)
public class FormularioApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormularioApplication.class, args);
	}

}
