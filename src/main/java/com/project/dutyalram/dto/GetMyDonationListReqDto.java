package com.projectnmt.dutyalram.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMyDonationListReqDto {
    public int userId;
    public int donationDate;
    public int mainCategoryId;
}
