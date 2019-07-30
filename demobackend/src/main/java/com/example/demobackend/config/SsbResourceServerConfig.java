package com.example.demobackend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

//
@Configuration
@EnableResourceServer
public class SsbResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler ssbAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler ssbAuthenticationFailureHandler;

//    @Autowired
//    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

//    @Bean
//    CorsAwareAuthenticationFilter corsFilter() {
//        CorsAwareAuthenticationFilter filter = new CorsAwareAuthenticationFilter();
//        return filter;
//    }
//
//    @Bean
//    CorsFilter corsFilter2() {
//        CorsFilter filter = new CorsFilter();
//        return filter;
//    }

//    @Autowired
//    SimpleCorsFilter filter;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 所以在我们的app登录的时候我们只要提交的action，不要跳转到登录页
        http
//                .addFilterBefore(filter, SessionManagementFilter.class)
                .cors().and().
                formLogin()
                .loginProcessingUrl("/user/login")
                .successHandler(ssbAuthenticationSuccessHandler)
                .failureHandler(ssbAuthenticationFailureHandler)
                .and()
                .logout().logoutUrl("/user/logout").permitAll()
        .and()
        ;

        http
//                .and()
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/register",
                        "/social/**",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.jpg",
                        "/**/*.png",
                        "/**/*.woff2",
                        "/code/image",
                        "/mock/**",
                        "/cmd202/**",
                        "/**"
                )
                .permitAll()//以上的请求都不需要认证
                .anyRequest()
                .authenticated()
                ;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
