package com.example.securityprj04.config;

import com.example.securityprj04.service.JpaUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

/** UserDetailsManager имплементится в InMemoryUserDetailsManager и в JdbcUserDetailsManager.
* А мы хотим JpaUserDetailsManager, которого нет. Запилим сами! Только не Manager, а Service, там реализовывать меньше*/
private final JpaUserDetailsService jpaUserDetailsService;

    public SecurityConfig(JpaUserDetailsService jpaUserDetailsService) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.ignoringAntMatchers("/h2-console/**")) //TODO не понимаю зачем игнорить тут, если дальше разрешаем
                .authorizeRequests(auth -> auth
                    .antMatchers("/h2-console/**").permitAll() //если поставить mvcMatchers, то не пустит
                    .mvcMatchers("/posts").permitAll() //а вот get на этот адрес можно без авторизации
                    .anyRequest().authenticated()) //все запросы должны быть авторизованы, но то что выше это исклчюения
                .userDetailsService(jpaUserDetailsService) //поскольку в этом классе всего один метод, то вызывается он
                .headers(headers -> headers.frameOptions().sameOrigin()) //TODO зачем это нужно?
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance(); //отрубает кодировку, не использовать в продашне
        return new BCryptPasswordEncoder();
    }

}
