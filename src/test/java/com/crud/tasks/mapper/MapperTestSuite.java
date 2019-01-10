package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MapperTestSuite {
    @Test
    public void testMapToBoard(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloBoardDto boardDto = new TrelloBoardDto("TestId","TestDTO",new ArrayList<>());
        TrelloBoardDto boardDto2 = new TrelloBoardDto("TestId2","TestDTO2",new ArrayList<>());
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        boardDtoList.add(boardDto);
        boardDtoList.add(boardDto2);
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(boardDtoList);
        Assert.assertEquals(2,trelloBoards.size());
        Assert.assertEquals("TestId2",trelloBoards.get(1).getId());
    }
    @Test
    public void testMapToBoardDto(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloBoard board = new TrelloBoard("Test id", "Test name",new ArrayList<>());
        TrelloBoard board2 = new TrelloBoard("Test id2", "Test name2",new ArrayList<>());
        List<TrelloBoard> boards = new ArrayList<>();
        boards.add(board);
        boards.add(board2);
        List<TrelloBoardDto> boardDtoList = trelloMapper.mapToBoardsDto(boards);
        Assert.assertEquals(2,boardDtoList.size());
        Assert.assertEquals("Test name",boardDtoList.get(0).getName());
    }
    @Test
    public void testMapToList(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloListDto listDto = new TrelloListDto("TestId","Test list",true);
        TrelloListDto listDto2 = new TrelloListDto("TestId2","Test list2",false);
        List<TrelloListDto> listDtos = new ArrayList<>();
        listDtos.add(listDto);
        listDtos.add(listDto2);
        List<TrelloList> lists = trelloMapper.mapToList(listDtos);
        Assert.assertEquals(2,lists.size());
        Assert.assertTrue(lists.get(0).isClosed());
    }
    @Test
    public void testMapToListDto(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloList list = new TrelloList("Some Id","Some name",true);
        TrelloList list2 = new TrelloList("Other Id","Other name",true);
        List<TrelloList> lists = new ArrayList<>();
        lists.add(list);
        lists.add(list2);
        List<TrelloListDto> listDtos = trelloMapper.mapToListDto(lists);
        Assert.assertEquals(2,listDtos.size());
        Assert.assertEquals("Other Id",listDtos.get(1).getId());
    }
    @Test
    public void testMapToCardDto(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCard card = new TrelloCard("Card name","Some description","Top","List1");
        TrelloCardDto cardDto = trelloMapper.mapToCardDto(card);
        Assert.assertEquals("Card name",cardDto.getName());
        Assert.assertEquals("Some description",cardDto.getDescription());
        Assert.assertEquals("Top",cardDto.getPos());
        Assert.assertEquals("List1",cardDto.getListId());
    }
    @Test
    public void testMapToCard(){
        TrelloMapper trelloMapper = new TrelloMapper();
        TrelloCardDto cardDto = new TrelloCardDto("Test name","A Dto card","Top","Any list");
        TrelloCard card = trelloMapper.mapToCard(cardDto);
        Assert.assertEquals("Test name",card.getName());
        Assert.assertEquals("A Dto card",card.getDescription());
        Assert.assertEquals("Top",card.getPos());
        Assert.assertEquals("Any list",card.getListId());
    }
}
