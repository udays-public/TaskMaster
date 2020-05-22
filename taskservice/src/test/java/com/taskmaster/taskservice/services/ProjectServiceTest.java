package com.taskmaster.taskservice.services;

import com.taskmaster.taskservice.models.Project;
import com.taskmaster.taskservice.repository.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    ProjectRepository repo;

    @InjectMocks
    ProjectService service;

    Project project;

    @BeforeEach
    void setUp() {
        project = new Project("office", "office work");

    }

    @Test
    void save() {

        given(repo.save(any(Project.class))).willAnswer(arg -> {
            return arg.getArguments()[0];
        });

        project = new Project("WFH", "office for work");

        Project newObj = service.save(project);
        assertEquals(newObj, project);

    }

    @Test
    void findAll() {
        given(repo.findAll()).willReturn(List.of(project));

        List<Project> all = service.findAll();

        assertThat(all).isNotNull();
        assertEquals(1, all.size());

    }

    @Test
    void findById() {
        given(repo.findById(1L)).willReturn(Optional.of(project));

        Optional<Project> project = service.findById(1L);

        assertThat(project).isNotEmpty();
        assertThat(project).hasValue(this.project);
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}