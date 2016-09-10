package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.pojo.entities.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 7/8/16.
 */
@Entity
public class Desease implements EntityInterface {
    @Id
    private String code;
    private String name;
    private String parent;

    public Desease() {

    }

    public Desease(Category category) {
        this.code = category.getMkbCode();
        this.name = category.getMkbName();
    }

    public static List<Desease> createDeseases(List<Category> categories) {
        List<Desease> deseases = new ArrayList<>();
        categories.stream().forEach(category -> {
            deseases.add(new Desease(category));
        });
        return deseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Desease desease = (Desease) o;

        return code != null ? code.equals(desease.code) : desease.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
