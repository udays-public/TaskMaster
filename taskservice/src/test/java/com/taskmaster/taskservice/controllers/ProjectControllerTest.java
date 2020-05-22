package com.taskmaster.taskservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmaster.taskservice.models.Project;
import com.taskmaster.taskservice.services.ProjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
class ProjectControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProjectService service;

    Project project;



    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {

        project = new Project("office","office work");

        Mockito.when(service.findAll()).thenReturn(List.of(project));
        Mockito.when(service.save(any(Project.class))).thenReturn(project);
    }

    @Test
    void create() throws Exception {

        mvc.perform(post("/projects/")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(project.getName()));
    }

    @Test
    void getAll() throws Exception {
        mvc.perform(get("/projects/")
                .contentType(MediaType.APPLICATION_JSON)).
                andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value(project.getName()));

    }

    @Test
    void update() {
    }

    @Test
    void getProject() {
    }

    @Test
    void delete() {
    }
}