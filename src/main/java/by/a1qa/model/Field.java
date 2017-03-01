package by.a1qa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by alexei.khilchuk on 22/01/2017.
 */
@Entity
@Table(name = "field")
public class Field {
    @Id
    @Column(name = "idField")
    private int idField;

    @Column(name = "idType")
    private int idType;

    @Column(name = "name")
    private String name;

    @Column(name = "idProject")
    private int idProject;

    @Column(name = "modelFieldName")
    private String modelFieldName;

    @Column(name = "required")
    private String required;

    @Column(name = "tooltip")
    private String tooltip;

    public String getModelFieldName() {
        return modelFieldName;
    }

    public void setModelFieldName(String modelFieldName) {
        this.modelFieldName = modelFieldName;
    }

    public int getIdField() {
        return idField;
    }

    public void setIdField(int idField) {
        this.idField = idField;
    }

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

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public String toString() {
        return "Field{" +
                "idField=" + idField +
                ", idType=" + idType +
                ", name='" + name + '\'' +
                ", idProject=" + idProject +
                ", modelFieldName='" + modelFieldName + '\'' +
                ", required='" + required + '\'' +
                ", tooltip='" + tooltip + '\'' +
                '}';
    }
}
