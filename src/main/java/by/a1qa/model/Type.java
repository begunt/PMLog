package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */

@Entity
@Table(name = "type")
public class Type {
    @Id
    @Column(name = "idType")
    private int idType;

    @Column(name = "name")
    private String name;

    public int getIdType() {
        return idType;
    }

    public void setIdType(int idType) {
        this.idType = idType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
