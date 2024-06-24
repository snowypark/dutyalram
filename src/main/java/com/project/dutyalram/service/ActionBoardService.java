package com.projectnmt.dutyalram.service;

import com.projectnmt.dutyalram.dto.resp.ActionBoardRespDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionBoardService {

    @Autowired
    private ActionBoardMapper actionBoardMapper;


    public void saveActionBoard(ActionBoardReqDto actionBoardReqDto) {
        actionBoardMapper.saveActionBoard(actionBoardReqDto.toEntity());
    }

    public List<ActionBoardRespDto> getActionBoardList(ActionBoardReqDto actionBoardReqDto) {
        List<ActionBoard> actionboardsList = actionBoardMapper.getActionBoardLIst(
                actionBoardReqDto.getActionBoardId(),
                actionBoardReqDto.getUserId(),
                actionBoardReqDto.getActionContent(),
                actionBoardReqDto.getImageId(),
                actionBoardReqDto.getImageURL(),
                actionBoardReqDto.getCreateDate(),
                actionBoardReqDto.getChallengePageId()

        );

        return actionboardsList.stream()
                .map(ActionBoard::toActionBoardListRespDto)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteActionBoard(int actionBoardId) {

        actionBoardMapper.deleteActionBoardById(actionBoardId);
    }

    public List<ActionBoardRespDto> getActionBoardByChallengePageId(int challengePageId ){
        List<ActionBoard> actionBoards = actionBoardMapper.getActionBoardByChallengePageId(challengePageId);
        return actionBoards.stream().map(ActionBoard::toActionBoardListRespDto).collect(Collectors.toList());
    }

    public int getParticipantCount(int challengePageId) {
        return actionBoardMapper.countByActionBoardPageId(challengePageId);
    }


}
