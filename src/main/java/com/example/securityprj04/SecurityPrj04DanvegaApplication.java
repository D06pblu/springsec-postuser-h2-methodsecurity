package com.example.securityprj04;

import com.example.securityprj04.model.Post;
import com.example.securityprj04.model.User;
import com.example.securityprj04.repository.PostRepo;
import com.example.securityprj04.repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecurityPrj04DanvegaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityPrj04DanvegaApplication.class, args);
    }

    @Bean
/**CLR выполняется после того как ApplicationContext стартует, но до того как метод main выполнится,
 * поэтому в него можно подать инстанс нашего PostRepo */
    CommandLineRunner commandLineRunner(PostRepo posts, UserRepo users, PasswordEncoder encoder) {
        return args -> {
            users.save(new User("user", encoder.encode("pass"), "ROLE_USER"));
            users.save((new User("admin", encoder.encode("pass"), "ROLE_USER,ROLE_ADMIN")));

            posts.save(new Post("Do6pblu", "Покатушки в Кировске", "snowboard",
                    "Приехали в Кировск катать сноуборды"));
            posts.save(new Post("Рулон Обоев", "Взяли новый объект", "плиточник",
                    "Приступил к отделке ванной на новом объекте"));
        };
    }

}
