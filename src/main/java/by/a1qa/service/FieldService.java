package by.a1qa.service;

import by.a1qa.dao.FieldDao;
import by.a1qa.model.Field;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tbegu_000 on 22.01.2017.
 */
@Service
@Transactional
public class FieldService {
    private FieldDao fieldDao;

    public void setFieldDao(FieldDao fieldDao) {
        this.fieldDao = fieldDao;
    }

    public void addField(Field field) {
        this.fieldDao.addField(field);
    }

    public void updateField(Field field) {
        this.fieldDao.updateField(field);
    }

    public void removeField(int id) {
        this.fieldDao.removeField(id);
    }

    public Field getFieldById(int id) {
        return this.fieldDao.getFieldById(id);
    }

    public List<Field> listFields() {
        return this.fieldDao.listFields();
    }

    public List<Field> listFieldsByIdProject(int idProject) {
        return this.fieldDao.listFieldsByIdProject(idProject);
    }
}
