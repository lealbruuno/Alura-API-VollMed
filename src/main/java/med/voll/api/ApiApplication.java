// Pacote que contém a classe principal da API
package med.voll.api;

// Importação de bibliotecas e classes necessárias
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Anotação que configura a classe como uma aplicação Spring Boot 
@SpringBootApplication
public class ApiApplication {

    // Método principal que inicia a aplicação Spring Boot
    public static void main(String[] args) {
        // Executa o método run da classe SpringApplication, passando a classe ApiApplication e os argumentos recebidos
        SpringApplication.run(ApiApplication.class, args);
    }

}