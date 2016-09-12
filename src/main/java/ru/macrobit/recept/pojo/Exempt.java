package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.*;
import java.util.List;

/**
 * Created by david on 11.07.16.
 */
@Entity
@Table(name = "exempt")
@TypeDefs({@TypeDef(name = "AddressObject", typeClass = Address.class)})
public class Exempt implements EntityInterface {
    @EmbeddedId
    @JsonIgnore
    private ExemptId doc;
    @Transient
    private String id;
    private String areaId;
    private String fileNumber;
    private String fileNumberPens;
    private Long dateBirth;
    private String gender;
    @Type(type = "AddressObject")
    private Address address;
    private String disabilityGroup;
    private String maxLg;
    private String status;
    private Long dateReg;
    private String pasportSeries;
    private String pasportNum;
    private String phone;
    private String snils;
    private String fio;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<ExemptCategory> categories;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Disease> diseases;
    @JsonIgnore
    @Transient
    private String categoryId;
    private Long lpuId;

    private String polisN;
    private String polisS;
    private Long dateLgBegin;
    private Long dateLgEnd;
    private boolean rejectLg;
    private String documentNumber;
    private String documentType;
    private boolean invalid;
    private String description;
    private String benefitDoc;
    private String benefitDocNum;
    private long categoryCode;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getFileNumber() {
        return fileNumber;
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber = fileNumber;
    }

    public String getFileNumberPens() {
        return fileNumberPens;
    }

    public void setFileNumberPens(String fileNumberPens) {
        this.fileNumberPens = fileNumberPens;
    }

    public Long getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Long dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDisabilityGroup() {
        return disabilityGroup;
    }

    public void setDisabilityGroup(String disabilityGroup) {
        this.disabilityGroup = disabilityGroup;
    }

    public String getMaxLg() {
        return maxLg;
    }

    public void setMaxLg(String maxLg) {
        this.maxLg = maxLg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDateReg() {
        return dateReg;
    }

    public void setDateReg(Long dateReg) {
        this.dateReg = dateReg;
    }

    public String getPasportSeries() {
        return pasportSeries;
    }

    public void setPasportSeries(String pasportSeries) {
        this.pasportSeries = pasportSeries;
    }

    public String getPasportNum() {
        return pasportNum;
    }

    public void setPasportNum(String pasportNum) {
        this.pasportNum = pasportNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getLpuId() {
        return lpuId;
    }

    public void setLpuId(Long lpuId) {
        this.lpuId = lpuId;
    }

    public String getPolisN() {
        return polisN;
    }

    public void setPolisN(String polisN) {
        this.polisN = polisN;
    }

    public String getPolisS() {
        return polisS;
    }

    public void setPolisS(String polisS) {
        this.polisS = polisS;
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

    public boolean isRejectLg() {
        return rejectLg;
    }

    public void setRejectLg(boolean rejectLg) {
        this.rejectLg = rejectLg;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<ExemptCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<ExemptCategory> categories) {
        this.categories = categories;
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

    public long getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(long categoryCode) {
        this.categoryCode = categoryCode;
    }

    public ExemptId getDoc() {
        return doc;
    }

    public void setDoc(ExemptId doc) {
        this.doc = doc;
    }

    public String getId() {
        return doc == null ? null : doc.getId() + ":::" + doc.getType().ordinal();
    }

    public void setId(String id) {
        this.id = id;
    }
}
