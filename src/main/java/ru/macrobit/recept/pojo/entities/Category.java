package ru.macrobit.recept.pojo.entities;

/**
 * Created by [david] on 09.09.16.
 */
public class Category {
    private String id;
    private String categoryCode;
    private String mkbCode;
    private String mkbName;
    private String deseaseName;
    private String drugs;
    private String sovmest;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getMkbCode() {
        return mkbCode;
    }

    public void setMkbCode(String mkbCode) {
        this.mkbCode = mkbCode;
    }

    public String getMkbName() {
        return mkbName;
    }

    public void setMkbName(String mkbName) {
        this.mkbName = mkbName;
    }

    public String getDeseaseName() {
        return deseaseName;
    }

    public void setDeseaseName(String deseaseName) {
        this.deseaseName = deseaseName;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
