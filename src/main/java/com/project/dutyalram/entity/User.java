package com.projectnmt.dutyalram.entity;

import com.projectnmt.dutyalram.security.PrincipalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class  User {

    private int Id;
    private String email;
    //비밀번호
    private String password;
    //닉네임
    private String name;
    //성별
    private String gender;
    //생년월일
    private int age;
    //프로필 이미지


    public PrincipalUser toPrincipalUser() {
        LocalDate now = LocalDate.now();
        String[] arr = age.split("-");
        int now_age = now.getYear() - Integer.parseInt(arr[0]) + 1;
        return PrincipalUser.builder()
                .userId(userId)
                .password(password)
                .name(name)
                .email(email)
                .gender(gender)
                .authorities(getAuthorities())
                .build();
    }
}
