package com.apps.conf;

import com.apps.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全配置
 *
 * @author SimonYang
 * @version 1.0.0
 * @date 2019-03-13
 */
@Slf4j
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // FIXME: 2019-03-22 密碼不加密 密碼應該存密文 待註冊功能完成後修正
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                // .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .and()
                .csrf().disable();

        // .antMatchers("/index").permitAll();
        // .authorizeRequests()
        // //我們指定任何用戶都可以訪問多個URL的模式。
        // //任何用戶都可以訪問以"/resources/","/signup", 或者 "/about"開頭的URL。
        // .antMatchers("/resources/**", "/signup", "/about").permitAll()
        // //以 "/admin/" 開頭的URL只能讓擁有 "ROLE_ADMIN"角色的用戶訪問。
        // //請注意我們使用 hasRole 方法，沒有使用 "ROLE_" 前綴。
        // .antMatchers("/admin/**").hasRole("ADMIN")
        // //任何以"/db/" 開頭的URL需要同時具有 "ROLE_ADMIN" 和 "ROLE_DBA"權限的用戶才可以訪問。
        // //和上面一樣我們的 hasRole 方法也沒有使用 "ROLE_" 前綴。
        // .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        // //任何以"/db/" 開頭的URL只需要擁有 "ROLE_ADMIN" 和 "ROLE_DBA"其中一個權限的用戶才可以訪問。
        // //和上面一樣我們的 hasRole 方法也沒有使用 "ROLE_" 前綴。
        // .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA")
        // //尚未匹配的任何URL都要求用戶進行身份驗證
        // .anyRequest().authenticated()
        // .and()
        // .formLogin()
        // //指定登錄頁的路徑
        // .loginPage("/login")
        // //指定登錄成功後跳轉到/index頁面
        // .defaultSuccessUrl("/index")
        // //指定登錄失敗後跳轉到/login?error頁面
        // .failureUrl("/login?error")
        // //必須允許所有用戶訪問我們的登錄頁（例如未驗證的用戶，否則驗證流程就會進入死循環）
        // //這個formLogin().permitAll()方法允許所有用戶基於表單登錄訪問/login這個page。
        // .permitAll()
        // .and()
        // //開啟cookie儲存用戶信息，並設置有效期為14天，指定cookie中的密鑰
        // .rememberMe().tokenValiditySeconds(1209600).key("mykey")
        // .and()
        // .logout()
        // //指定登出的url
        // .logoutUrl("/api/user/logout")
        // //指定登場成功之後跳轉的url
        // .logoutSuccessUrl("/index")
        // .permitAll()
        // .and()
        // .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/api/**/**");
    }
}
