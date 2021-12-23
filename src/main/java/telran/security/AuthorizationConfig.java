package telran.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity // -> говорим, что именно этот класс сейчас отвечает за security авторизацию вместо своих дефолтных
@EnableGlobalMethodSecurity(prePostEnabled = true) // -> // можем писать защиту на уровне методов
public class AuthorizationConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web){
        // игнорировать этот эндпоинт для security
//        web.ignoring().antMatchers(HttpMethod.POST, "/account/register");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // объект HttpSecurity http -> отвечает за авторизацию
        http.httpBasic();
        http.csrf().disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS); // отказываемся от cookies
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/account/register/**")
                .permitAll()
                .antMatchers( "/forum/posts/**")
                .permitAll()
                .antMatchers("/account/user/{login}/role/{role}")
                .hasRole("ADMINISTRATOR")
                .anyRequest() // любой запрос
                .authenticated(); // только с аутитификацией

    }

}
