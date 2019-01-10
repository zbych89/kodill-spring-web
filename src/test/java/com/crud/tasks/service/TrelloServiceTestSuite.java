package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService service;
    @Mock
    private TrelloClient trelloClient;
    @Mock
    private SimpleEmailService emailService;

    @Test
    public void testFetchTrelloBoards(){
        TrelloBoardDto boardDto = new TrelloBoardDto("1","Board 1",new ArrayList<>());
        TrelloBoardDto boardDto2 = new TrelloBoardDto("2","Board 2",new ArrayList<>());
        TrelloBoardDto boardDto3 = new TrelloBoardDto("3","Board 3",new ArrayList<>());
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(boardDto);
        boardDtoList.add(boardDto2);
        boardDtoList.add(boardDto3);
        Mockito.when(trelloClient.getTrelloBoards()).thenReturn(boardDtoList);
        List<TrelloBoardDto> resultList = service.fetchTrelloBoards();
        Assert.assertEquals(3,resultList.size());
        Assert.assertEquals(0,resultList.get(0).getLists().size());
        Assert.assertEquals("Board 3",resultList.get(2).getName());
    }
    @Test
    public void testCreateTrelloCard(){
        TrelloCardDto cardDto = new TrelloCardDto("Card","Test","top","List 1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1","Card","url");
        Mockito.when(trelloClient.createNewCard(cardDto)).thenReturn(createdCard);
        CreatedTrelloCardDto result = service.createTrelloCard(cardDto);
        Assert.assertEquals("1",result.getId());
    }
}
