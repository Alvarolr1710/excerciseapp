package readingList;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

//http://localhost:8080/
@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class ReadListApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReadListApplication.class, args);
    }

}
