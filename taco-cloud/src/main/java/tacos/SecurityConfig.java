package tacos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/admin/**").hasRole("ADMIN") // /admin 요청은 ADMIN 권한을 가진 사용자만 요청가능
                        .requestMatchers("/user/**").authenticated()   // /user 요청은 인증 된 사용자만 요청가능
                        .requestMatchers("/post/new").authenticated()   // /post/new 요청은 인증 된 사용자만 요청가능
                        .requestMatchers("/post/update").authenticated()   // /post/new 요청은 인증 된 사용자만 요청가능
                        .requestMatchers("/**").permitAll() // 그 외 모든 요청에 대해서는 모든 사용자 요청가능
                )
                .formLogin(login -> login
                        .loginPage("/login")	// 커스텀 로그인 페이지 지정
                        .usernameParameter("username")	// 뷰에서 받아 올 username 파라미터 이름
                        .passwordParameter("password")	// 뷰에서 받아 올 password 파라미터 이름
                        .defaultSuccessUrl("/user", true)   //로그인 성공 시 이동할 URI
                        .permitAll()
                )
                .logout()
                .logoutSuccessUrl("/")  //로그아웃 성공 시 이동할 URI
                .and()
                .exceptionHandling(h -> h.accessDeniedPage("/error"));
        return http.build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        //인메모리에 USER 권한 계정 생성
        UserDetails user = User.builder()
                .username("user")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("USER")
                .build();
        //인메모리에 ADMIN 권한 계정 생성
        UserDetails admin = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
