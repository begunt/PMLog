package by.a1qa.dao;

import by.a1qa.model.Field;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by tbegu_000 on 22.01.2017.
 */
public class FieldDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(FieldDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addField(Field field) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(field);
        logger.info("Field successfully saved. Field details: " + field);
    }


    public void updateField(Field field) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(field);
        logger.info("Field successfully update. Field details: " + field);
    }


    public void removeField(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Field field = (Field) session.load(Field.class, new Integer(id));

        if(field!=null){
            session.delete(field);
        }
        logger.info("Field successfully removed. Field details: " + field);
    }


    public Field getFieldById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Field field = (Field) session.load(Field.class, new Integer(id));
        logger.info("Field successfully loaded. Field details: " + field);

        return field;
    }

    public List<Field> listFields() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Field> fieldList = session.createQuery("from Field").list();

        for(Field field: fieldList){
            logger.info("field list: " + field);
        }

        return fieldList;
    }
}
