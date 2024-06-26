package com.example.blog.domain.member.controller;

import com.example.blog.domain.email.EmailService;
import com.example.blog.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignForm signForm) {
        memberService.signup(signForm.getUsername(), signForm.getPassword(), signForm.getNickname(), signForm.getEmail(), signForm.getTypeCode());
        emailService.send(signForm.getEmail(), "서비스가입을 환영합니다!", "회원가입을 축하드립니다~^^");
        return "redirect:/member/login";
    }
    @ToString
    @Getter
    @Setter
    public static class SignForm {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String password_confirm;

        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        private String TypeCode;
    }
}
