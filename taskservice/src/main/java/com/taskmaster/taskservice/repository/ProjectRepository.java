package com.taskmaster.taskservice.repository;

import com.taskmaster.taskservice.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository  extends JpaRepository<Project,Long> {
}
