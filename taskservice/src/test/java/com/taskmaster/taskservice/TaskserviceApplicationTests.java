package com.taskmaster.taskservice;

import com.taskmaster.taskservice.controllers.ProjectController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TaskserviceApplicationTests {

	@Autowired
	ProjectController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
