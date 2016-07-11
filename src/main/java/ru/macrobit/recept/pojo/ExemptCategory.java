package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by david on 11.07.16.
 */
@Entity
public class ExemptCategory implements EntityInterface {
    @Id
    private Long id;
    private String code;
    private String name;
    private String drugs;
    private String sovmest;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getSovmest() {
        return sovmest;
    }

    public void setSovmest(String sovmest) {
        this.sovmest = sovmest;
    }
}
