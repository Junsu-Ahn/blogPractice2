package com.example.blog.global.security;

import com.example.blog.domain.member.entity.Member;
import com.example.blog.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final MemberService memberService;

    // 카카오톡 로그인이 성공할 때 마다 이 함수가 실행된다.
    @Override
    @Transactional
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String oauthId = oAuth2User.getName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        Map attributesProperties = (Map) attributes.get("properties");
        String nickname = (String) attributesProperties.get("nickname");

        String providerTypeCode = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        String username = providerTypeCode + "__%s".formatted(oauthId);

        Optional<Member> existingMember = memberService.findByUsername(username);
        if (existingMember.isPresent()) {
            return new CustomOAuth2User(existingMember.get().getUsername(), existingMember.get().getPassword(), new ArrayList<>());
        }

        // 새로운 회원 생성
        Member member = memberService.whenSocialLogin(providerTypeCode, username, nickname);

        List<GrantedAuthority> authorityList = new ArrayList<>();

        return new CustomOAuth2User(member.getUsername(), member.getPassword(), authorityList);
    }
}


class CustomOAuth2User extends User implements OAuth2User {

    public CustomOAuth2User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public String getName() {
        return getUsername();
    }
}