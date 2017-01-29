package by.a1qa.service;

import by.a1qa.dao.ProjectDao;
import by.a1qa.model.Project;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Service
@Transactional
public class ProjectService {
    private ProjectDao projectDao;

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public void addProject(Project project) {
        this.projectDao.addProject(project);
    }

    public void updateProject(Project project) {
        this.projectDao.updateProject(project);
    }

    public void removeProject(int id) {
        this.projectDao.removeProject(id);
    }

    public Project getProjectById(int id) {
        return this.projectDao.getProjectById(id);
    }

    public Project getProjectByName(String name) {
        return this.projectDao.getProjectByName(name);
    }

    public List<Project> listProjects() {
        return this.projectDao.listProjects();
    }
}
