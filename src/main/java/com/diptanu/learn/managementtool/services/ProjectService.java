package com.diptanu.learn.managementtool.services;

import com.diptanu.learn.managementtool.domain.Project;
import com.diptanu.learn.managementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;


    public Project saveOrUpdateProject(Project project) {

        return projectRepository.save(project);
    }
}
