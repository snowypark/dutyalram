package com.projectnmt.dutyalram.controller;

import com.projectnmt.dutyalram.aop.annotation.ValidAspect;
import com.projectnmt.dutyalram.dto.EditAccountReqDto;
import com.projectnmt.dutyalram.dto.GetMyDonationListReqDto;

import com.projectnmt.dutyalram.security.PrincipalUser;
import com.projectnmt.dutyalram.service.AccountService;
import com.projectnmt.dutyalram.service.PrincipalService;
import com.projectnmt.dutyalram.service.TeamService;
import com.projectnmt.dutyalram.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.BindingResult;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    PrincipalService getPrincipalService;
    @Autowired
    TeamService teamService;
    @Autowired
    UserService userSerive;
    @Autowired
    AccountService accountService;


    @GetMapping("/principal")
    public ResponseEntity<?> getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        return ResponseEntity.ok(principalUser);
    }

    @ValidAspect
    @PutMapping("/mypage/edit")
    public ResponseEntity<?> accountEdit(@Valid @RequestBody EditAccountReqDto editAccountReqDto,
                                         BindingResult bindingResult) {
        accountService.editAccount(editAccountReqDto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/teams")
    public ResponseEntity<?> getTeamList (SearchTeamListDto searchTeamListDto){
        List<Team> teamList = teamService.getTeamList(searchTeamListDto);
        return ResponseEntity.ok(teamList);
    }



    @GetMapping("/message/{id}/{isTeam}")
    public ResponseEntity<?> getMessageList(@PathVariable int id, @PathVariable int isTeam) {
        return ResponseEntity.ok(userSerive.getMessageList(id, isTeam));
    }

    @DeleteMapping("/message/delete/{id}/{isTeam}")
    public ResponseEntity<?> deleteMessageList(@PathVariable int id, @PathVariable int isTeam) {
        userSerive.deleteMessageBYId(id, isTeam);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/mypage/donation")
    public ResponseEntity<?> getMyList(GetMyDonationListReqDto getMyDonationListReqDto) {
        return ResponseEntity.ok(accountService.GetMyDonation(getMyDonationListReqDto));
    }
    @GetMapping("/mypage/participate")
    public ResponseEntity<?> getMyPaticipateCount(int userId) {
        return ResponseEntity.ok(userSerive.getParticipateCount(userId));
    }
    @ValidAspect
    @PutMapping("/mypage/edit/password")
    public ResponseEntity<?> passwordedit(@Valid @RequestBody EditAccountReqDto editAccountReqDto,
                                          BindingResult bindingResult) {
        accountService.editPassword(editAccountReqDto);
        return ResponseEntity.ok().build();
    }
}

