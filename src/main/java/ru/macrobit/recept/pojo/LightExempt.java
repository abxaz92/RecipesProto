package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by [david] on 10.09.16.
 */
@Entity
@Table(name = "exempt")
@TypeDefs({@TypeDef(name = "AddressObject", typeClass = Address.class)})
public class LightExempt {
    @JsonIgnore
    @EmbeddedId
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
    @Transient
    private int type;

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

    public String getId() {
        return doc == null ? null : doc.getId() + ":::" + doc.getType().ordinal();
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExemptId getDoc() {
        return doc;
    }

    public void setDoc(ExemptId doc) {
        this.doc = doc;
    }

    public int getType() {
        return doc != null ? doc.getType().ordinal() : null;
    }

    public void setType(int type) {
        this.type = type;
    }
}
