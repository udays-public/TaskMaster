package com.taskmaster.taskservice.controllers;

import com.taskmaster.taskservice.models.State;
import com.taskmaster.taskservice.models.Task;
import com.taskmaster.taskservice.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TasksController {

    @Autowired
    TaskService service;

    @PostMapping("/projects/{projectid}/tasks")
    Task Create(@PathVariable String projectid, @RequestBody Task task) {
        return service.create(projectid,task);
    }


    @GetMapping("/projects/{projectId}/tasks")
    List<Task> getAll(@PathVariable String projectId) {
        var projId = Long.parseLong(projectId);
        return service.findByProjectId(projId);
    }

    @GetMapping("projects/{projectid}/tasks/{taskid}")
    Optional<Task> getTask(@PathVariable String projectid, @PathVariable String taskid) {
        return  service.getTask(projectid,taskid);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/projects/{projectId}/tasks/{taskId}")
    void delete(@PathVariable String projectId, @PathVariable String taskid) {
        service.delete(projectId,taskid);

    }

}
