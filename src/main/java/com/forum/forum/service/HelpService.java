package com.forum.forum.service;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.HelpMessageInputDto;
import com.forum.forum.model.HelpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpService {
    private HelpMessageDao helpMessageDao;

    @Autowired
    public HelpService(HelpMessageDao helpMessageDao) {
        this.helpMessageDao = helpMessageDao;
    }

    public void addHelpMessage(HelpMessageInputDto helpMessageDto){
        HelpMessage helpMessage = new HelpMessage.Builder()
                .description(helpMessageDto.getDescription())
                .subject(helpMessageDto.getSubject())
                .build();
        helpMessageDao.save(helpMessage);
    }

    public void deleteHelpMessageById(Long id){
        helpMessageDao.deleteById(id);
    }

    public List<HelpMessage> getPageOfHelpMessage(Pageable pageable){
        return helpMessageDao.findAll(pageable).stream()
                .collect(Collectors.toList());
    }
}
