package ru.macrobit.recept.pojo.drug;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by david on 12.07.16.
 */
@Entity
@Table(name = "DRUG_FARMGROUP")
public class FarmGroup implements EntityInterface {
    @Id
    private Long id;
    @Column(length = 50)
    private String name;
    @Column(length = 200)
    private String fname;

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

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }
}
