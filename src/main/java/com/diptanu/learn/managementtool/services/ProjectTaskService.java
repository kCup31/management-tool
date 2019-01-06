package com.diptanu.learn.managementtool.services;

import com.diptanu.learn.managementtool.domain.Backlog;
import com.diptanu.learn.managementtool.domain.ProjectTask;
import com.diptanu.learn.managementtool.repositories.BacklogRepository;
import com.diptanu.learn.managementtool.repositories.ProjectTaskRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {

    @Autowired
    private BacklogRepository backlogRepository;

    @Autowired
    private ProjectTaskRepository projectTaskRepository;

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {

        // PTs (project Tasks) to be added to a specific project, project !=null, Backlog exists
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        // Set the backlog to the project task.
        projectTask.setBacklog(backlog);

        // Set the projectSequence in project task. Projectsequence to be like IDPRO-1, IDPRO-2 ... IDPRO-100
        Integer backlogSequence = backlog.getPTSequence();

        // Update the backlog sequence
        backlogSequence++;

        backlog.setPTSequence(backlogSequence);

        // Add initial sequence to Project task
        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // Initial priority when priority null
        if (projectTask.getPriority() == null ) {
            projectTask.setPriority(3);
        }

        // Initial status when status is null
        if (projectTask.getStatus()=="" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }
}
