package com.projectnmt.dutyalram.dto;

import lombok.Data;

@Data
public class RegisterTeamReqDto {
    int userId;
    String teamName;
    boolean teamType;
    int teamTypeCategory;
    String teamPhoneNumber;
    String teamEmail;
    String companyRegisterNumber;
    String companyRegisterNumberCopyUrl;
    String teamHomepage;
    String teamInfoText;
    String teamLogoImgUrl;
    Account[] accountInfos;

    public Team toEntity() {
        return Team.builder()
                .teamName(teamName)
                .teamType(teamType ? 1 : 2)
                .teamTypeCategory(teamTypeCategory)
                .teamPhoneNumber(teamPhoneNumber)
                .teamEmail(teamEmail)
                .companyRegisterNumber(companyRegisterNumber)
                .companyRegisterNumberUrl(companyRegisterNumberCopyUrl)
                .teamHomepage(teamHomepage)
                .teamInfoText(teamInfoText)
                .teamLogoImgUrl(teamLogoImgUrl)
                .build();
    }
}
