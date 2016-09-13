package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.pojo.entities.Category;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 11.07.16.
 */
@Entity
public class ExemptCategory implements EntityInterface {
    @Id
    @JsonProperty("id")
    private String code;
    private String name;
    private String drugs;
    private String sovmest;
    private int type;

    public ExemptCategory() {

    }

    public ExemptCategory(Category category) {
        this.name = category.getDeseaseName();
        this.code = category.getCategoryCode();
        this.drugs = category.getDrugs();
        this.sovmest = category.getSovmest();
    }

    public static List<ExemptCategory> createCategories(List<Category> categories) {
        List<ExemptCategory> exemptCategories = new ArrayList<>();
        categories.stream().forEach(category -> {
            exemptCategories.add(new ExemptCategory(category));
        });
        return exemptCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExemptCategory that = (ExemptCategory) o;

        return code != null ? code.equals(that.code) : that.code == null;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
