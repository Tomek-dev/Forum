package com.forum.forum.service;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.model.HelpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

    public void addHelpMessage(HelpMessage helpMessage){
        helpMessageDao.save(helpMessage);
    }

    public void deleteHelpMeassageById(long id){
        helpMessageDao.deleteById(id);
    }

    public List<HelpMessage> getPageOfHelpMessage(int page){
        long count = helpMessageDao.count();
        if(page < 0 || page> Math.ceil((double) count/15)){
            throw new IndexOutOfBoundsException("Page index out of bounds");
        }
        return helpMessageDao.findAll(PageRequest.of(page, 15, Sort.by("id").descending())).stream()
                .collect(Collectors.toList());
    }
}
