package com.dasom.task.kakaoPay.config;

import com.dasom.task.kakaoPay.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private final UserService userService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/" , "/login").permitAll() .and()
                .formLogin() // 하위에 내가 직접 구현한 로그인 폼, 로그인 성공시 이동 경로 설정 가능. , 로그인 폼의 아이디,패스워드는 username, password로 맞춰야 함
                .loginPage("/login") // 로그인이 수행될 경로.
                .loginProcessingUrl("/loginProcess")// 로그인form의  action과 일치시켜주어야 함.
                .defaultSuccessUrl("/loginSuccess") // 로그인 성공 시 이동할 경로.
                .failureUrl("/login?error=true") // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/accessDenied_page"); // 권한이 없는 대상이 접속을시도했을 때
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("[ BEAN ] : passwordEncoder");
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 로그인 성공 처리를 위한 Handler
    @Bean
    public void successHandler() {
        log.info("[ BEAN ] : AuthenticationSuccessHandler");
        // loginIdname, defaultUrl
    }

    // 실패 처리를 위한 Handler
    @Bean
    public void failureHandler() {
        log.info("[ BEAN ] : failureHandler");
    }
}