package ru.macrobit.recept.pojo.drug;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by david on 12.07.16.
 */
@Entity
@Table(name = "DRUG_MNAME")
public class MnnName implements EntityInterface{
    @Id
    private Long id;
    private String name;
    private String nameL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameL() {
        return nameL;
    }

    public void setNameL(String nameL) {
        this.nameL = nameL;
    }
}
