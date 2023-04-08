package dgtic.core.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    AuthenticationManager authen;
    @Autowired
    private UserDetailsService ser;
    @Bean
    protected PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

//    @Bean
//    protected InMemoryUserDetailsManager configureAuthentication(){
//        List<UserDetails> userDetails=new ArrayList<>();
//        List<GrantedAuthority> adminRole=new ArrayList<>();
//        adminRole.add(new SimpleGrantedAuthority("admin"));
//        List<GrantedAuthority> userRole=new ArrayList<>();
//        userRole.add(new SimpleGrantedAuthority("user"));
//
//        userDetails.add(new User("admin","$2a$10$WjvmHf4gJ5yhMOrKAEF2k.SIhMQ/ubR1.Zrbl3rv7pRNq3S1j7U2O",adminRole));
//        userDetails.add(new User("user","$2a$10$.05MYZKSG7Eh8/L8qGqt7OMS9AlIbdNJPNSO.vVthQmvsad2HX/c6",userRole));
//        return new InMemoryUserDetailsManager(userDetails);
//    }



    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder bul = http.getSharedObject(AuthenticationManagerBuilder.class);
        bul.userDetailsService(ser);
        authen = bul.build();

        http.authorizeRequests()
                .antMatchers("/estudiantes/**").hasAuthority("admin")
                .and()
                .formLogin().loginPage("/login")
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/error_403")
                .and()
                .authenticationManager(authen);
        return http.build();
    }

}
