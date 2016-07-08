package ru.macrobit.recept.pojo;

import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.*;

/**
 * Created by david on 7/8/16.
 */
@Entity
public class Doctor implements EntityInterface {
    private Long id;
    private String secondName;
    private String middleName;
    private String post;
    private String speciality;
    private String codeDoctor;
    private Lpu lpu;
    private String codePost;
    private Long dateOrder;
    private String dateCertificate;
    private Long dateEntry;
    private Long dateRemoval;
    private String codeSpeciality;
    private String codeDoctorFull;
    private String OGRN_LPU;
    private String OKATO;
    @SuppressWarnings("unused")
    private String fio;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getCodeDoctor() {
        return codeDoctor;
    }

    public void setCodeDoctor(String codeDoctor) {
        this.codeDoctor = codeDoctor;
    }

    public String getCodePost() {
        return codePost;
    }

    public void setCodePost(String codePost) {
        this.codePost = codePost;
    }

    public String getCodeSpeciality() {
        return codeSpeciality;
    }

    public Long getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Long dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getDateCertificate() {
        return dateCertificate;
    }

    public void setDateCertificate(String dateCertificate) {
        this.dateCertificate = dateCertificate;
    }

    public Long getDateEntry() {
        return dateEntry;
    }

    public void setDateEntry(Long dateEntry) {
        this.dateEntry = dateEntry;
    }

    public Long getDateRemoval() {
        return dateRemoval;
    }

    public void setDateRemoval(Long dateRemoval) {
        this.dateRemoval = dateRemoval;
    }

    public void setCodeSpeciality(String codeSpeciality) {
        this.codeSpeciality = codeSpeciality;
    }

    public String getCodeDoctorFull() {
        return codeDoctorFull;
    }

    public void setCodeDoctorFull(String codeDoctorFull) {
        this.codeDoctorFull = codeDoctorFull;
    }

    public String getOGRN_LPU() {
        return OGRN_LPU;
    }

    public void setOGRN_LPU(String oGRN_LPU) {
        OGRN_LPU = oGRN_LPU;
    }

    public String getOKATO() {
        return OKATO;
    }

    public void setOKATO(String oKATO) {
        OKATO = oKATO;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Lpu getLpu() {
        return lpu;
    }

    public void setLpu(Lpu lpu) {
        this.lpu = lpu;
    }
}