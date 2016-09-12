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
public class Disease implements EntityInterface {
    @Id
    private String code;
    private String name;
    private String parent;

    public Disease() {

    }

    public Disease(Category category) {
        this.code = category.getMkbCode();
        this.name = category.getMkbName();
    }

    public static List<Disease> createDeseases(List<Category> categories) {
        List<Disease> diseases = new ArrayList<>();
        categories.stream().forEach(category -> {
            diseases.add(new Disease(category));
        });
        return diseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Disease disease = (Disease) o;

        return code != null ? code.equals(disease.code) : disease.code == null;
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
