package com.taskmaster.taskservice.services;

import com.taskmaster.taskservice.models.Project;
import com.taskmaster.taskservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository repo;

    public Project save(Project project) {

        // TODO : Do some validation

        return repo.save(project);

    }


    public boolean checkOwner(String projectId, Authentication authentication)
    {
        return repo.getOne(Long.parseLong(projectId)).getCreatedBy() .equals(authentication.getName());
    }

    public List<Project> findAll(String name) {
        return repo.findByCreatedBy(name);
    }

    public Optional<Project> findById(long projectId) {
        return repo.findById(projectId);
    }

    public void deleteById(long projId) {
        repo.deleteById(projId);
    }

    public Project update(String projectId, Project request) {
        return repo.findById(Long.parseLong(projectId)).map(project -> {
            project.setDescription(request.getDescription());
            project.setName(request.getName());
            return save(project);
        }).orElseThrow(() -> new RuntimeException(String.format("project by id {} not found", projectId)));
    }
}
