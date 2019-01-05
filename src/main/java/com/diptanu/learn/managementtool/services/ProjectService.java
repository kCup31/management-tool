package com.diptanu.learn.managementtool.services;

import com.diptanu.learn.managementtool.domain.Backlog;
import com.diptanu.learn.managementtool.domain.Project;
import com.diptanu.learn.managementtool.exception.ProjectIdException;
import com.diptanu.learn.managementtool.repositories.BacklogRepository;
import com.diptanu.learn.managementtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BacklogRepository backlogRepository;


    public Project saveOrUpdateProject(Project project) {

        try {

            String identifier = project.getProjectIdentifier().toUpperCase();
            project.setProjectIdentifier(identifier);

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(identifier);
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(identifier));
            }

            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("ProjectId : " + project.getProjectIdentifier().toUpperCase() + " already exists");
        }
    }

    public Project findByProjectIdentifier(String projectId) {

        return Optional.ofNullable(projectRepository.findByProjectIdentifier(projectId.toUpperCase()))
                .orElseThrow(() -> new ProjectIdException("ProjectId " + projectId + " does not exists"));

    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Optional.ofNullable(projectRepository.findByProjectIdentifier(projectId)).ifPresent(project -> projectRepository.delete(project));
    }
}
