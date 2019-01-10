package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTestSuite {
    @InjectMocks
    private DbService service;
    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks(){
        Task task = new Task(1L,"A title","A content");
        Task task2 = new Task(2L,"A new title","A new content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(task2);
        Mockito.when(repository.findAll()).thenReturn(tasks);
        List<Task> result = service.getAllTasks();
        Assert.assertEquals(2,result.size());
        Assert.assertEquals(1L,(long)result.get(0).getId());
        Assert.assertEquals("A new title",result.get(1).getTitle());
    }
    @Test
    public void testGetTask(){
        Optional<Task> task = Optional.ofNullable(new Task(1L,"Test task","Test content"));
        Mockito.when(repository.findById(anyLong())).thenReturn(task);
        Optional<Task> result = service.getTask(3L);
        Assert.assertTrue(result.isPresent());
        Assert.assertEquals("Test content",result.get().getContent());
    }
    @Test
    public void testSaveTask(){
        Task task = new Task(1L,"Title","Content");
        Mockito.when(repository.save(task)).thenReturn(task);
        Task result = service.saveTask(task);
        Assert.assertEquals("Title",result.getTitle());
    }
    @Test
    public void testDeleteTask(){
        Task task = new Task(1L,"Title","Content");
        service.deleteTask(task.getId());
        Mockito.verify(repository,Mockito.times(1)).deleteById(1L);
    }
}
