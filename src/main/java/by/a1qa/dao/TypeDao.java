package by.a1qa.dao;

import by.a1qa.model.Type;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Repository
public class TypeDao {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TypeDao.class);
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addType(Type type) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(type);
        logger.info("Type successfully saved. Type details: " + type);
    }


    public void updateType(Type type) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(type);
        logger.info("Type successfully update. Type details: " + type);
    }


    public void removeType(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Type type = (Type) session.load(Type.class, new Integer(id));

        if(type!=null){
            session.delete(type);
        }
        logger.info("Type successfully removed. Type details: " + type);
    }


    public Type getTypeById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Type type = (Type) session.load(Type.class, new Integer(id));
        logger.info("Type successfully loaded. Type details: " + type);

        return type;
    }


    public List<Type> listTypes() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Type> typeList = session.createQuery("from type").list();

        for(Type type: typeList){
            logger.info("type list: " + type);
        }

        return typeList;
    }
}
