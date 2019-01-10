package com.crud.tasks.scheduler;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyObject;

@RunWith(MockitoJUnitRunner.class)
public class SchedulerTest {
    @InjectMocks
    private EmailScheduler scheduler;
    @Mock
    private TaskRepository repository;
    @Mock
    private SimpleEmailService emailService;
    @Mock
    private AdminConfig adminConfig;

    @Test
    public void testSendInformation(){
        Mockito.when(repository.count()).thenReturn(2L);
        Mockito.when(adminConfig.getAdminMail()).thenReturn("test.com");
        scheduler.sendInformationEmail();
        Mockito.verify(emailService,Mockito.times(1)).send(anyObject());
    }
}
