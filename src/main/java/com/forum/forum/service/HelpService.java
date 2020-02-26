package com.forum.forum.service;

import com.forum.forum.dao.HelpMessageDao;
import com.forum.forum.model.HelpMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void deleteHelpMeassage(HelpMessage helpMessage){
        helpMessageDao.delete(helpMessage);
    }
}
