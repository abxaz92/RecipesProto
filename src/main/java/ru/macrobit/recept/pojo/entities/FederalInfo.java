package ru.macrobit.recept.pojo.entities;

/**
 * Created by [david] on 10.09.16.
 */
public class FederalInfo {
    private String snils;
    private long categoryCode;
    private String benefitDoc;
    private String benefitDocNum;
    private Long dateLgBegin;
    private Long dateLgEnd;

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(long categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getBenefitDoc() {
        return benefitDoc;
    }

    public void setBenefitDoc(String benefitDoc) {
        this.benefitDoc = benefitDoc;
    }

    public String getBenefitDocNum() {
        return benefitDocNum;
    }

    public void setBenefitDocNum(String benefitDocNum) {
        this.benefitDocNum = benefitDocNum;
    }

    public Long getDateLgBegin() {
        return dateLgBegin;
    }

    public void setDateLgBegin(Long dateLgBegin) {
        this.dateLgBegin = dateLgBegin;
    }

    public Long getDateLgEnd() {
        return dateLgEnd;
    }

    public void setDateLgEnd(Long dateLgEnd) {
        this.dateLgEnd = dateLgEnd;
    }
}