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
@Table(name = "DRUG_VOLUME")
public class Volume implements EntityInterface {
    @Id
    private Long id;
    @Column(length = 100)
    private String name;

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
}
