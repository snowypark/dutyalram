package com.projectnmt.dutyalram.service;

import com.projectnmt.dutyalram.dto.RegisterTeamReqDto;
import com.projectnmt.dutyalram.entity.*;
import com.projectnmt.dutyalram.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    public void saveTeam(RegisterTeamReqDto registerTeamReqDto) {
        Team team = registerTeamReqDto.toEntity();
        int successCount = 0;
        successCount += teamMapper.saveTeam(team);
        successCount += teamMapper.saveLeader(registerTeamReqDto.getUserId(), team.getTeamId());
        User user = userMapper.findUserByUserId(registerTeamReqDto.getUserId());
        if(user.getRoleRegisters().stream().filter(authority -> authority.getRoleId() == 2).collect(Collectors.toList()).isEmpty()) {
            successCount += userMapper.saveRole(registerTeamReqDto.getUserId(), 2);
        } else {
            successCount++;
        }
        for(Account account : registerTeamReqDto.getAccountInfos()) {
            account.setTeamId(team.getTeamId());
            successCount += teamMapper.saveAccount(account);
        }
        if(successCount < 3 + registerTeamReqDto.getAccountInfos().length) {
            throw new UsernameNotFoundException("");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void joinTeam(Map<String, Integer> map) {
        int successCount = 0;
        successCount += teamMapper.saveMember(map.get("userId"), map.get("teamId"));
        User user = userMapper.findUserByUserId(map.get("userId"));
        if(user.getRoleRegisters().stream().filter(authority -> authority.getRoleId() == 2).collect(Collectors.toList()).isEmpty()) {
            successCount += userMapper.saveRole(map.get("userId"), 2);
        } else {
            successCount++;
        }
        if(successCount < 2 ) {
            throw new UsernameNotFoundException("");
        }
    }
    public void updateTeam(UpdateTeamReqDto updateTeamReqDto) {
        Team team = updateTeamReqDto.toEntity();
        int successCount = 0;
        successCount += teamMapper.updateTeam(team);
        teamMapper.deleteAccounts(team.getTeamId());
        for (Account account : updateTeamReqDto.getAccountInfos()) {
            account.setTeamId(team.getTeamId());
            successCount += teamMapper.saveAccount(account);
        }
    }
    public List<Team> getTeamList(SearchTeamListDto searchTeamListDto) {
        List<Team> teamList = teamMapper.teamList(searchTeamListDto.getUserId());
        for (Team team : teamList) {
            List<TeamMember> teamMembers = teamMapper.findMemberByTeamId(team.getTeamId());
            team.setTeamMembers(teamMembers);
        }
        return teamList;
    }
    public Team getTeamInfo(SearchTeamInfoDto searchTeamInfoDto) {
        Team team = teamMapper.teamInfo(searchTeamInfoDto.getTeamId());
        return team;
    }
    public List<TeamMember> getMemberInfo(TeamMemberListReqDto teamMemberListReqDto) {
        List<TeamMember> teamMembers = new ArrayList<>();
        for (int userId : teamMemberListReqDto.getUserId()) {
            teamMembers.add(teamMapper.findMember(userId, teamMemberListReqDto.getTeamId()));
        }
        return teamMembers;
    }
    public List<Donation> getDonationList(int teamId) {

        return  teamMapper.getDonationListByTeamId(teamId);
    }

    public List<ChallengePage> getChallengeList(int teamId) {
        return teamMapper.getChallengeListByTeamId(teamId);
    }

    // 팀 멤버인지 확인하는 메소드
    public boolean isTeamMember(int teamId, int userId) {
        TeamMember teamMember = teamMapper.findMemberByTeamIdAndUserId(teamId, userId);
        return teamMember != null && teamMember.getUserId() == userId;
    }

    public void updatePageDelete(int pageId) {
        teamMapper.updatePageDelete(pageId);
    }
}
