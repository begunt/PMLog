package by.a1qa.service;

import by.a1qa.dao.TypeDao;
import by.a1qa.model.Type;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Service
@Transactional
public class TypeService {
    private TypeDao typeDao;

    public void setTypeDao(TypeDao typeDao) {
        this.typeDao = typeDao;
    }

    public void addType(Type type) {
        this.typeDao.addType(type);
    }

    public void updateType(Type type) {
        this.typeDao.updateType(type);
    }

    public void removeType(int id) {
        this.typeDao.removeType(id);
    }

    public Type getTypeById(int id) {
        return this.typeDao.getTypeById(id);
    }

    public List<Type> listTypes() {
        return this.typeDao.listTypes();
    }
}
