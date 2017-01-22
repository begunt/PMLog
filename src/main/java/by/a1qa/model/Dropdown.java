package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */

@Entity
@Table(name = "dropdown")
public class Dropdown {

    @Id
    @Column
    private int idDropdown;

    @Column
    private int idField;

    @Column
    private String itemName;

    public int getIdDropdown() {
        return idDropdown;
    }

    public void setIdDropdown(int idDropdown) {
        this.idDropdown = idDropdown;
    }

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
