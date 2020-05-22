package com.taskmaster.taskservice.services;

import com.taskmaster.taskservice.models.State;
import com.taskmaster.taskservice.models.Task;
import com.taskmaster.taskservice.repository.ProjectRepository;
import com.taskmaster.taskservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    TaskRepository repo;

    @Autowired
    ProjectRepository projectRepo;

    public Task create(String projectid, Task task) {
        return projectRepo.findById(Long.parseLong(projectid)).map(project -> {
            // TODO : Do some validation
            task.setProject(project);
            task.setState(State.PENDING);
            return repo.save(task);
        }).orElseThrow(() -> new RuntimeException(String.format(" project by Id: {} not found ", projectid)));
    }

    public List<Task> findByProjectId(long projId) {
        return repo.findByProjectId(projId);
    }


    public Optional<Task> getTask(String projectid, String taskid) {
        return projectRepo.findById(Long.parseLong(projectid)).map(project -> {
            //TODO : check for permission in proejct. two sql calls can be avoided
            return repo.findByIdAndProjectId(Long.parseLong(taskid), Long.parseLong(projectid));
        }).orElseThrow(() -> new RuntimeException(String.format(" project by Id: {} not found ", projectid)));

    }

    public void delete(String projectId, String taskid) {
        projectRepo.findById(Long.parseLong(projectId)).map(project -> {
            //TODO : check for permission in proejct
            repo.deleteByIdAndProjectId(Long.parseLong(taskid), Long.parseLong(projectId));
            return null;
        }).orElseThrow(() -> new RuntimeException(String.format(" project by Id: {} not found ", projectId)));
    }
}
