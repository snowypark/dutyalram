package com.projectnmt.dutyalram.dto;

import lombok.Data;

@Data
public class AdminTeamListReqDto {
    private int userId;
    private int selectedCategory;
    private int selectedSearchTextOption;
    private int searchCount;
    private String searchText;
    private int pageNumber;
}
