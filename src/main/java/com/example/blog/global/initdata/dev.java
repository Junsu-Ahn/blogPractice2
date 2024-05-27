package com.example.blog.global.initdata;


import com.example.blog.domain.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Profile("dev")
public class dev {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Bean
    public ApplicationRunner init(MemberService memberService)
    {
        return args -> {
            memberService.signup("admin", passwordEncoder.encode("admin"), "admin", "admin@test.conm");
            memberService.signup("user1", passwordEncoder.encode("user1"), "user1", "user1@test.conm");
            memberService.signup("user2", passwordEncoder.encode("user2"), "user2", "user2@test.conm");
        };
    }
}
