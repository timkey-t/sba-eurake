package com.timkey.server.conf;


import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

/**
 * @author tangfeng
 * @fileName SecuritySecureConfig
 * @date 2019/6/3 17:03
 * @description spring boot admin 简单配置
 */
@Configuration
public class SecuritySecureConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminServerProperties adminServerProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String adminContestPath = adminServerProperties.getContextPath();
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(adminContestPath + "/");
        http.authorizeRequests()
                .antMatchers(adminContestPath + "/assets/**").permitAll()
                .antMatchers(adminContestPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContestPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContestPath + "/logout").and()
                .httpBasic().and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringAntMatchers(
                        adminContestPath + "/instances",
                        adminContestPath + "/actuator/**"
                );
    }
}
