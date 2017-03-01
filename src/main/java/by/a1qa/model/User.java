package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by t.begun on 01.03.2017.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "idUser")
    private int idUser;

    @Column(name = "login")
    private String login;
}
