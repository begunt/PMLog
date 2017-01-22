package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tbegu_000 on 06.11.2016.
 */
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "idEmployee")
    private int idEmployee;

    @Column(name = "idRole")
    private int idRole;

}
