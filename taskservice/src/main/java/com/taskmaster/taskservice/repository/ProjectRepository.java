package com.taskmaster.taskservice.repository;

import com.taskmaster.taskservice.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository  extends JpaRepository<Project,Long> {

    List<Project> findByCreatedBy(String name);
}
