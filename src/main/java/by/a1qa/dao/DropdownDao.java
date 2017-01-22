package by.a1qa.dao;

import by.a1qa.model.Dropdown;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by tbegu_000 on 22.01.2017.
 */
public class DropdownDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DropdownDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addDropdown(Dropdown dropdown) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(dropdown);
        logger.info("Dropdown successfully saved. Dropdown details: " + dropdown);
    }


    public void updateDropdown(Dropdown dropdown) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(dropdown);
        logger.info("Dropdown successfully update. Dropdown details: " + dropdown);
    }


    public void removeDropdown(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Dropdown dropdown = (Dropdown) session.load(Dropdown.class, new Integer(id));

        if(dropdown!=null){
            session.delete(dropdown);
        }
        logger.info("Dropdown successfully removed. Dropdown details: " + dropdown);
    }


    public Dropdown getDropdownById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Dropdown dropdown = (Dropdown) session.load(Dropdown.class, new Integer(id));
        logger.info("Dropdown successfully loaded. Dropdown details: " + dropdown);

        return dropdown;
    }

    public List<Dropdown> listDropdowns() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Dropdown> dropdownList = session.createQuery("from Dropdown").list();

        for(Dropdown dropdown: dropdownList){
            logger.info("dropdown list: " + dropdown);
        }

        return dropdownList;
    }
}
