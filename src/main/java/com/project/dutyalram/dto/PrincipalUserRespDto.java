package com.projectnmt.dutyalram.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PrincipalUserRespDto {
    private int userId;
    private String username;
    private String name;
    private String email;
    private String phoneNumber;
    private String gender;
    private int age;
    private String profileImg;

}
