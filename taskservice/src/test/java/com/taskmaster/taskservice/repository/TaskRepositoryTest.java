package com.taskmaster.taskservice.repository;

import com.taskmaster.taskservice.configuration.JpaAuditConfig;
import com.taskmaster.taskservice.models.Project;
import com.taskmaster.taskservice.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@Import(JpaAuditConfig.class)
class TaskRepositoryTest {


    @Autowired
    TestEntityManager entityManager;

    @Autowired
    TaskRepository taskRepository;


    Project project;
    Task task;

    @BeforeEach
    void setUp() {
        project = new Project("office","office work");

        entityManager.persist(project);


        task = new Task();
        task.setProject(project);
        task.setName("Attand Meeting");
        task.setDescription("Meeting for back up restore");
        task.setTargetDate(new Date());

        entityManager.persist(task);


    }

    @Test
    void findByProjectId() {

        var tasks = taskRepository.findByProjectId(project.getId());
        assertTrue(tasks.size() != 0);
        assertEquals(tasks.get(0), task);
    }

    @Test
    void findByIdAndProjectId() {

        var tasks = taskRepository.findByIdAndProjectId(task.getId(), project.getId());
        assertTrue(tasks.isPresent());
        assertEquals(tasks.get(), task);

    }

    @Test
    void deleteByIdAndProjectId() {

        Task task = new Task();
        task.setProject(project);
        task.setName("Complete testing");
        task.setDescription("complete testing");
        task.setTargetDate(new Date());

        entityManager.persist(task);

        taskRepository.deleteByIdAndProjectId(task.getId(), project.getId());
        var tasks = taskRepository.findByIdAndProjectId(task.getId(), project.getId());

        assertTrue(tasks.isEmpty());

    }
}