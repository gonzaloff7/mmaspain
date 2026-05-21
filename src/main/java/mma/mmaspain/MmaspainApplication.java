package mma.mmaspain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MmaspainApplication {

	public static void main(String[] args) {

	    ConexionTest.probarConexion();

	    SpringApplication.run(
	            MmaspainApplication.class,
	            args
	    );
	}
}
