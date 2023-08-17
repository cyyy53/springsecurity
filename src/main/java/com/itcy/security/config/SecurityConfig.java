package com.itcy.security.config;

import com.itcy.security.filter.JwtAuthenticationTokenFilter;
import com.itcy.security.handler.AccessDeniedHandlerImpl;
import com.itcy.security.handler.AuthenticationEntryPointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @BelongsProject: security
 * @BelongsPackage: com.itcy.security.config
 * @Author: cuiYong
 * @CreateTime: 2023-08-11  17:13
 * @Description: TODO
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
//开启注解权限功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  {
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    private AccessDeniedHandlerImpl accessDeniedHandler;

    @Autowired
    private AuthenticationEntryPointImpl authenticationEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable() // 关闭csrf自定义登录
                // 不通过session获取securitycontext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/user/login").anonymous() // 对于登录接口,允许匿名访问
                        .anyRequest().authenticated() // 除了上面的所有请求需要全部鉴权认证
                        //把jwt过滤器放到user和pass后面

                ).addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                //配置权限不足处理器
                .accessDeniedHandler(accessDeniedHandler)
                //配置认知失败处理器
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .cors()
                .and()
                .build();


    }







}
