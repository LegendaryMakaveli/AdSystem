package com.adSystems;


import com.adSystems.datas.models.Role;
import com.adSystems.datas.models.SubscriptionPlan;
import com.adSystems.datas.models.User;
import com.adSystems.datas.repositories.UserRepository;
import com.adSystems.util.PasswordHash;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Main { public static void main(String[] args) {SpringApplication.run(Main.class, args);}

    @Bean
    CommandLineRunner createAdmin(UserRepository repo) {
        return makaveli -> {
            if (!repo.existsByEmail("briankachelhoffer698@gmail.com")) {
                User admin = new User();
                admin.setFirstName("Brian");
                admin.setLastName("Kachelhoffer");
                admin.setAddress("Route 205 Fullerton, CA United State");
                admin.setEmail("briankachelhoffer698@gmail.com");
                admin.setPassword(PasswordHash.hash("MakaveliAdmin@1"));
                admin.setRole(Role.ADMIN);
                admin.setSubscriptionPlan(SubscriptionPlan.PREMIUM);
                repo.save(admin);
            }
        };
    }

}
