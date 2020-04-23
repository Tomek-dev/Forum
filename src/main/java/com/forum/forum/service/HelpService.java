package com.forum.forum.service;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.dto.HelpMessageInputDto;
import com.forum.forum.dto.HelpOutputDto;
import com.forum.forum.model.HelpMessage;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpService {

    private final ModelMapper mapper = new ModelMapper();

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

    public List<HelpOutputDto> getPageOfHelpMessage(Pageable pageable){
        Pageable pageableValue = PageRequest.of(pageable.getPageNumber()-1, pageable.getPageSize(), pageable.getSort());
        return helpMessageDao.findAll(pageableValue).stream()
                .map(help -> mapper.map(help, HelpOutputDto.class))
                .collect(Collectors.toList());
    }

    public long getPageNumber(Pageable pageable){
        return (long) Math.ceil((double) helpMessageDao.count()/pageable.getPageSize());
    }
}
