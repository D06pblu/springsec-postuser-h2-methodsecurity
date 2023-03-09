Чуть посложнее пример.
Используем SpringDataJPA, но на H2 DB
1. сделали обычный rest api
2. сделали SecConfig, а в нем SecurityFilterChain, накрутили разрешений каких нам нужно
3. делаем SecurityUser implements UserDetails, это principal который будет жить в токенах
4. делаем JpaUserDetailsService implements UserDetailsService, единственная задача которого
искать в юзера в БД по имени и отдавать UserDetails
5. добавляем в SecurityFilterChain настроечку .userDetailsService(jpaUserDetailsService), перед этим,
ессно, автовайрим наш бин из п.4
6. через CommandLineRunner в мэйне добавляем в БД данные
7. добавляем в HomeController анноташки @PreAuthorize("hasRole('ROLE_ХХХ')"), которые определяют какие
роли для того или иного метода нужны. В секьюрити конфиг надо добавить @EnableMethodSecurity,
иначе не заработает
8. добавляем в SecurityFilterChain настройку логина через .formLogin(Customizer.withDefaults())
или httpBasic()
9. добавляем бин PasswordEncoder в секьюрити конфиг, а в мэйне при создании юзера через CLR, добавляем
в аргументы его же, и вызываем кодировку пароля у ей внутре
