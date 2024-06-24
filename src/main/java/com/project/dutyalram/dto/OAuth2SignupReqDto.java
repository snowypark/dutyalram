package com.projectnmt.dutyalram.dto;

import com.projectnmt.dutyalram.entity.User;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class OAuth2SignupReqDto {

    @Pattern(regexp = "^[A-Za-z0-9]{4,10}$", message = "영문자, 숫자, 5 ~ 10자리 형식이여야 합니다." )
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{7,128}$", message = "영문자, 숫자, 특수문자를 포함한 8 ~ 128자리 형식이여야 합니다.")
    private String password; //1q2w3e4r!
    @Pattern(regexp = "^[ㄱ-힣]{1,10}$", message = "한글문자 2 ~ 10자리 형식이여야 합니다." )
    private String name;

    @NotBlank
    private String oauth2Name;
    @NotBlank
    private String providerName;

//    @NotEmpty
    @Email(regexp = "^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$", message = "이메일 형식이여야 합니다." )
    private String email;
    public User toEntity(BCryptPasswordEncoder passwordEncoder){
        return User.builder()
                .username(oauth2Name)
                .password(passwordEncoder.encode(oauth2Name))
                .age(LocalDate.now().toString())
                .profileImg("https://firebasestorage.googleapis.com/v0/b/react-study-20240226-jsm-4f336.appspot.com/o/library%2Fbook%2Fcover%2F5b2b3bc5-a9fd-4efa-9d71-6f8ee3c92765_%ED%95%98%EB%8A%98%EB%8B%A4%EB%9E%8C%EC%A5%90_%EA%B7%B8%EB%A6%BC_%EA%B0%95%EC%A2%8C%EB%8C%80%EC%A7%80_10-removebg-preview.png?alt=media&token=a241c611-ae05-4a85-b58f-fe47a53c0ced")
                .build();
    }

    public OAuth2 toOAuth2Entity(int userId) {
        return OAuth2.builder()
                .oAuth2Name(oauth2Name)
                .userId(userId)
                .providerName(providerName)
                .build();
    }
}
