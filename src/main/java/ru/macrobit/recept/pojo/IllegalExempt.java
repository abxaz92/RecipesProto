package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Transient;

/**
 * Created by [david] on 12.09.16.
 */
@Entity
public class IllegalExempt implements EntityInterface {
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

    public IllegalExempt() {

    }

    public IllegalExempt(Exempt exempt) {
        this.doc = exempt.getDoc();
        this.areaId = exempt.getAreaId();
        this.fileNumber = exempt.getFileNumber();
        this.fileNumberPens = exempt.getFileNumberPens();
        this.dateBirth = exempt.getDateBirth();
        this.gender = exempt.getGender();
        this.address = exempt.getAddress();
        this.disabilityGroup = exempt.getDisabilityGroup();
        this.maxLg = exempt.getMaxLg();
        this.status = exempt.getStatus();
        this.dateReg = exempt.getDateReg();
        this.pasportSeries = exempt.getPasportSeries();
        this.pasportNum = exempt.getPasportNum();
        this.phone = exempt.getPhone();
        this.snils = exempt.getSnils();
        this.fio = exempt.getFio();
        this.lpuId = exempt.getLpuId();
        this.polisN = exempt.getPolisN();
        this.polisS = exempt.getPolisS();
        this.dateLgBegin = exempt.getDateLgBegin();
        this.dateLgEnd = exempt.getDateLgEnd();
        this.rejectLg = exempt.isRejectLg();
        this.documentNumber = exempt.getDocumentNumber();
        this.documentType = exempt.getDocumentType();
        this.invalid = exempt.isInvalid();
        this.description = exempt.getDescription();
        this.benefitDoc = exempt.getBenefitDoc();
        this.benefitDocNum = exempt.getBenefitDocNum();
        this.categoryCode = exempt.getCategoryCode();
    }

    public String getId() {
        return doc == null ? null : doc.getId() + ":::" + doc.getType().ordinal();
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @JsonIgnore
    public String getCompositeId() {
        return this.snils + " " + this.pasportNum + " " + this.pasportSeries;
    }

    public ExemptId getDoc() {
        return doc;
    }

    public void setDoc(ExemptId doc) {
        this.doc = doc;
    }
}
