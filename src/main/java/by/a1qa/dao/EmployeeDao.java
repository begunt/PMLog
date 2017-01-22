package by.a1qa.dao;

import by.a1qa.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Repository
public class EmployeeDao {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmployeeDao.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void addEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(employee);
        logger.info("Employee successfully saved. Employee details: " + employee);
    }


    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(employee);
        logger.info("Employee successfully update. Employee details: " + employee);
    }


    public void removeEmployee(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, new Integer(id));

        if(employee!=null){
            session.delete(employee);
        }
        logger.info("Employee successfully removed. Employee details: " + employee);
    }


    public Employee getEmployeeById(int id) {
        Session session =this.sessionFactory.getCurrentSession();
        Employee employee = (Employee) session.load(Employee.class, new Integer(id));
        logger.info("Employee successfully loaded. Employee details: " + employee);

        return employee;
    }


    public List<Employee> listEmployees() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createQuery("from Employee").list();

        for(Employee employee: employeeList){
            logger.info("employee list: " + employee);
        }

        return employeeList;
    }

/*
    public List<Employee> listManagers() {
        List<Employee> managerList = getRoleList(1);

        return managerList;
    }


    public List<Employee> listEnginers() {
        List<Employee> managerList = getRoleList(2);

        return managerList;
    }

    public List<Employee> getRoleList (int idRole){
        List<Employee> employeeList = listEmployees();
        List<Employee> roleList = new ArrayList<Employee>();
        Employee role = new Employee();

        for (int i=0; i < employeeList.size(); i++){
            if(employeeList.get(i).getIdRole() == idRole) {
                role = employeeList.get(i);
                roleList.add(role);
            }
        }
        return roleList;
    }
    */
}

