package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskMapperTestSuite {
    @Test
    public void mapToTaskTest(){
        TaskMapper mapper = new TaskMapper();
        TaskDto dto = new TaskDto(1L,"Test","Content");
        Task task = mapper.mapToTask(dto);
        Assert.assertEquals(1L,(long)task.getId());
        Assert.assertEquals("Test",task.getTitle());
        Assert.assertEquals("Content",task.getContent());
    }
    @Test
    public void mapToTaskDtoTest() {
        TaskMapper mapper = new TaskMapper();
        Task task = new Task(1L, "Test", "Content");
        TaskDto dto = mapper.mapToTaskDto(task);
        Assert.assertEquals(1L, (long) dto.getId());
        Assert.assertEquals("Test", dto.getTitle());
        Assert.assertEquals("Content", dto.getContent());
    }
    @Test
    public void mapToTaskDtoList(){
        TaskMapper mapper = new TaskMapper();
        Task task = new Task(1L, "Test", "Content");
        Task task2 = new Task(2L, "Test2", "Content2");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);
        List<TaskDto> dtos = mapper.mapToTaskDtoList(tasks);
        Assert.assertEquals(2,dtos.size());
        Assert.assertEquals("Test",dtos.get(0).getTitle());
        Assert.assertEquals("Content2",dtos.get(1).getContent());
    }
}
