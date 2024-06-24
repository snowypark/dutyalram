package com.projectnmt.dutyalram.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditAccountReqDto {
    private int userId;
    private String username;
    private String oldPassword;
    private String newPassword;
    private String newPasswordCheck;
    private String name;
    private String email;
    private String age;
    private String gender;
    private String phonenumber;
    private String profileImg;
}
