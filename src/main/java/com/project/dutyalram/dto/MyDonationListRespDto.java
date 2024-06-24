package com.projectnmt.dutyalram.dto;

import lombok.Data;

@Data
public class MyDonationListRespDto {
    private int donationPageId;
    private String donationDate;
    private String storyTitle;
    private int amount;
}
