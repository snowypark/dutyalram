package com.projectnmt.dutyalram.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PrincipalUser implements UserDetails {
    private int userId;
    //유저아이디
    private String username;
    //비밀번호
    private String password;
    //닉네임
    private String name;
    //휴대폰 번호
    private String phoneNumber;
    //이메일
    private String email;
    //성별
    private String gender;
    //나이
    private int age;
    //프로필 이미지
    private String profileImg;
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
    //계정 사용시간 만료
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    //계정 잠금
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호 사용기간 만료
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    //계정 비활성
    @Override
    public boolean isEnabled() {
        return true;
    }
}
