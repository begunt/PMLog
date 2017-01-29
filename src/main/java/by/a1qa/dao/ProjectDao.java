package by.a1qa.dao;

import by.a1qa.model.Project;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Repository
public class ProjectDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addProject(Project project) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(project);
        logger.info("Project successfully saved. Project details: " + project);
    }

    public void updateProject(Project project) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(project);
        logger.info("Project successfully update. Project details: " + project);
    }

    public void removeProject(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, new Integer(id));

        if(project!=null){
            session.delete(project);
        }
        logger.info("Project successfully removed. Project details: " + project);
    }

    public Project getProjectById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, new Integer(id));
        logger.info("Project successfully loaded. Project details: " + project);

        return project;
    }

    public Project getProjectByName(String name) {
        Session session = this.sessionFactory.getCurrentSession();
        Criteria crit = session.createCriteria(Project.class);
        crit.add(Restrictions.eq("name", name));
        return (Project) crit.uniqueResult();
    }

    public List<Project> listProjects() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Project> projectList = session.createQuery("from Project").list();

        for(Project project: projectList){
            logger.info("project list: " + project);
        }

        return projectList;
    }
}
