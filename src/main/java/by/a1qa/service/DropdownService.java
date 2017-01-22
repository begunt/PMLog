package by.a1qa.service;

import by.a1qa.dao.DropdownDao;
import by.a1qa.model.Dropdown;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tbegu_000 on 22.01.2017.
 */
@Service
@Transactional
public class DropdownService {
    private DropdownDao dropdownDao;

    public void setDropdownDao(DropdownDao dropdownDao) {
        this.dropdownDao = dropdownDao;
    }

    public void addDropdown(Dropdown dropdown) {
        this.dropdownDao.addDropdown(dropdown);
    }

    public void updateDropdown(Dropdown dropdown) {
        this.dropdownDao.updateDropdown(dropdown);
    }

    public void removeDropdown(int id) {
        this.dropdownDao.removeDropdown(id);
    }

    public Dropdown getDropdownById(int id) {
        return this.dropdownDao.getDropdownById(id);
    }

    public List<Dropdown> listDropdowns() {
        return this.dropdownDao.listDropdowns();
    }

}
