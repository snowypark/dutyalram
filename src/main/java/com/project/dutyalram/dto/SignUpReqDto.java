package com.projectnmt.dutyalram.dto;

import com.projectnmt.dutyalram.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class SignUpReqDto {
    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자 5 ~ 10자리 형식이어야 합니다")
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "하나의 영문자, 숫자, 특수문자를 포함한 5 ~ 128자리 형식이어야 합니다")
    private String password;    // 1q2w3e4r!
    @Pattern(regexp = "^[a-zA-z0-9가-힇]{2,10}$", message = "2~10자 사이의 형식이어야 합니다(특수문자 X).")
    private String name;
    @Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", message = "전화번호 형식이어야 합니다.")
    private String phoneNumber;
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{1,3}$", message = "이메일 형식이어야 합니다")
    private String email;
    private String gender;
    private String age;
    private String profileImg;


    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
            .username(username)
            .password(passwordEncoder.encode(password))
            .name(name)
            .phoneNumber(phoneNumber)
            .email(email)
            .gender(gender)
            .age(age)
            .profileImg(profileImg)
            .build();
    }
}
