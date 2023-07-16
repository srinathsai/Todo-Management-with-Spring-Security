package net.javaguides.todo.config;


import lombok.AllArgsConstructor;
import net.javaguides.todo.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(); //for encoding raw password into Bcrypt form as Spring accepts only that form.
    }

    private CustomUserDetailsService customUserDetailsService; //no need to mention because of dependency injection springboot-6 will understand that authentication manager method uses this object details for customer details.
@Bean
SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.csrf().disable().authorizeHttpRequests((authorize)-> {
        /*authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN");
        authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");  //These are the ways to manage adding, changing and deleting todos acess based on user roles .
        authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN"); //This implies that the user with admin roles will have acess to update, delete and add todo.
        authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");// For giving acess to multiple users based on respective roles.
        authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasAnyRole("ADMIN","USER");*/
        authorize.anyRequest().authenticated();
    }).httpBasic(Customizer.withDefaults());   //Enabling only Http basic authentication.

    return http.build();
}

@Bean
public AuthenticationManager authenticationManager (AuthenticationConfiguration configuration) throws Exception{
    return  configuration.getAuthenticationManager();
}

/*
@Bean

    public UserDetailsService userDetailsService() {

        UserDetails srinath = User.builder().username("srinath").password(passwordEncoder().encode("lelouch")).roles("USER").build();

        UserDetails admin = User.builder().username("user").password(passwordEncoder().encode("admin")).roles("ADMIN").build(); //for handling multiple users with their roles
        //This bean will authenticate based on their respective username and password.

        return new InMemoryUserDetailsManager(srinath,admin);

    }

 */
}



