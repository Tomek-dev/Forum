package com.forum.forum.service;

import com.forum.forum.dao.TopicDao;
import com.forum.forum.other.SearchSpecification;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class SearchSerivceTests {

    @Mock
    private TopicDao topicDao;

    @InjectMocks
    private SearchSerivce searchSerivce;

    @Test
    public void shouldReturnPageNumber(){
        //given
        SearchSpecification searchSpecification = new SearchSpecification("query", "java", true);
        given(topicDao.count(Mockito.any(SearchSpecification.class))).willReturn(34L);

        //then
        assertEquals(3, searchSerivce.getPageNumber(searchSpecification, PageRequest.of(1,15)));
        assertEquals(4, searchSerivce.getPageNumber(searchSpecification, PageRequest.of(1,10)));
        assertEquals(2, searchSerivce.getPageNumber(searchSpecification, PageRequest.of(1,20)));
    }
}