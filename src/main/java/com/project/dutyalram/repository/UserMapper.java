package com.project.dutyalram.repository;

import com.projectnmt.dutyalram.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface  UserMapper {
    public User findUserTest();
    public User findUserByUsername(String username);
    public int saveUser(User user);
    public int updateUser(User user);
    public int saveRole(int userId, int roleId);
    public int deleteAuthority(int userId);
    public int deleteUserByUserId (Integer userId);
    public User findUserByUserId(int userId);
    public int deleteTeamByTeamId(int TeamId);
    public int deleteTeamMemberByTeamId(int TeamId);
    public int deleteTeamMemberByUserId(int TeamId);
    public int deleteOAuth2ByUserId(int userId);
    public void sendMessage(int userId, String message);
    public User findUserByOAuth2name(String name);
    public int deleteMessageById(int id, int isTeam);
    public int updatePassword(User user);
}