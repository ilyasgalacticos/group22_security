package kz.bitlab.group22.config;

import kz.bitlab.group22.beans.UserBean;
import kz.bitlab.group22.beans.UserBeanImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"kz.bitlab.group22"})
public class BeansConfig {

    @Bean
    @Scope("singleton")
    public UserBean userBean(){
        return new UserBeanImpl();
    }

}
