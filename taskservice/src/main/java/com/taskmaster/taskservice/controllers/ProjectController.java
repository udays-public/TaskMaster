package com.taskmaster.taskservice.controllers;

import com.taskmaster.taskservice.models.Project;
import com.taskmaster.taskservice.services.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "projects")
@Api(description = "API for Project resource")
public class ProjectController {

    @Autowired
    ProjectService projectService;


    @PostMapping
    @ApiOperation("Create Project resource ")
    @ResponseStatus(HttpStatus.CREATED)
    Project Create(@RequestBody Project project) {
        return projectService.save(project);
    }


    @GetMapping("/")
    @ApiOperation("Get All Project resources ")
    List<Project> GetAll(Authentication authentication) {
        return projectService.findAll(authentication.getName());
    }

    @PutMapping("/{projectId}")
    @ApiOperation("Update Project resource ")
    @PreAuthorize("@projectService.checkOwner(#projectId,authentication)")
    Project Update(@PathVariable String projectId, @RequestBody Project request) {
        return projectService.update(projectId, request);
    }

    @GetMapping("/{projectId}")
    @ApiOperation("Get Project resource by Id")
    Optional<Project> GetProject(@PathVariable String projectId) {
        var projId = Long.parseLong(projectId);
        return projectService.findById(projId);

    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{projectId}")
    @PreAuthorize("@projectService.checkOwner(#projectId,authentication)")
    void Delete(@PathVariable String projectId) {
        var projId = Long.parseLong(projectId);
        projectService.deleteById(projId);

    }
}
