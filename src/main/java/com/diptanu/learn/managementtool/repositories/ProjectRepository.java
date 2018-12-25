package com.diptanu.learn.managementtool.repositories;

import com.diptanu.learn.managementtool.domain.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String projectId);
}
