package by.a1qa.service;

import by.a1qa.dao.EmployeeDao;
import by.a1qa.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Service
@Transactional
public class EmployeeService {

    private EmployeeDao employeeDao;

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void addEmployee(Employee employee) {
        this.employeeDao.addEmployee(employee);
    }

    public void updateEmployee(Employee employee) {
        this.employeeDao.updateEmployee(employee);
    }

    public void removeEmployee(int id) {
        this.employeeDao.removeEmployee(id);
    }

    public Employee getEmployeeById(int id) {
        return this.employeeDao.getEmployeeById(id);
    }

    public List<Employee> listEmployees() {
        return this.employeeDao.listEmployees();
    }

/*
    public List<Employee> listManagers() {
        return this.employeeDao.listManagers();
    }


    public List<Employee> listEnginers() {
        return this.employeeDao.listEnginers();
    }
*/
}
