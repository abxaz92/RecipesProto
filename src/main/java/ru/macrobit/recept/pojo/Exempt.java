package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by david on 11.07.16.
 */
@Entity
public class Exempt implements EntityInterface {
    @Id
    private Long id;
    private String areaId;
    private String fileNumber;
    private String fileNumberPens;
    private Long dateBirth;
    private String gender;
    @ManyToOne(fetch = FetchType.EAGER)
    private Address address;
    private String disabilityGroup;
    private String maxLg;
    private String status;
    private Long dateReg;
    private String pasportSeries;
    private String pasportNum;
    private String phone;
    private String snils;
    private String type;
    private String fio;
    @ManyToOne(fetch = FetchType.EAGER)
    private ExemptCategory category;
    @ManyToOne(fetch = FetchType.EAGER)
    private Desease desease;
    private String lpuId;

    private String polisN;
    private String polisS;
    private Long dateLgBegin;
    private Long dateLgEnd;
    private boolean rejectLg;
    private String documentNumber;
    private String documentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public ExemptCategory getCategory() {
        return category;
    }

    public void setCategory(ExemptCategory category) {
        this.category = category;
    }

    public Desease getDesease() {
        return desease;
    }

    public void setDesease(Desease desease) {
        this.desease = desease;
    }

    public String getLpuId() {
        return lpuId;
    }

    public void setLpuId(String lpuId) {
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
}
