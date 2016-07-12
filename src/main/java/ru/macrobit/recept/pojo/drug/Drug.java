package ru.macrobit.recept.pojo.drug;

import org.hibernate.annotations.GenericGenerator;
import ru.macrobit.recept.abstracts.EntityInterface;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by [david] on 12.07.16.
 */
@Entity
public class Drug implements EntityInterface {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Long nomCode;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    private TorgName torgName;

    @ManyToOne(fetch = FetchType.EAGER)
    private MnnName mnnName;

    @ManyToOne(fetch = FetchType.EAGER)
    private Form form;

    private float dose;
    @ManyToOne(fetch = FetchType.EAGER)
    private Dose doseType;
    private float doseNum;

    @ManyToOne(fetch = FetchType.EAGER)
    private Volume vol;
    private float volNum;

    @ManyToOne(fetch = FetchType.EAGER)
    private Weight weight;
    private float weightNum;

    private String firm;
    private String firmCountry;
    private String pack;
    private String packFirm;
    private String packFirmCountry;
    private String compl;

    private boolean flagKek;
    private boolean flagJVNLS;
    private boolean forbid;
    private Date dateInclude;
    private Date dateExclude;
    private Long code;
    private String documents;

    @ManyToOne(fetch = FetchType.EAGER)
    private FarmGroup farmGroup;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getNomCode() {
        return nomCode;
    }

    public void setNomCode(Long nomCode) {
        this.nomCode = nomCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TorgName getTorgName() {
        return torgName;
    }

    public void setTorgName(TorgName torgName) {
        this.torgName = torgName;
    }

    public MnnName getMnnName() {
        return mnnName;
    }

    public void setMnnName(MnnName mnnName) {
        this.mnnName = mnnName;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public float getDose() {
        return dose;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

    public Dose getDoseType() {
        return doseType;
    }

    public void setDoseType(Dose doseType) {
        this.doseType = doseType;
    }

    public float getDoseNum() {
        return doseNum;
    }

    public void setDoseNum(float doseNum) {
        this.doseNum = doseNum;
    }

    public Volume getVol() {
        return vol;
    }

    public void setVol(Volume vol) {
        this.vol = vol;
    }

    public float getVolNum() {
        return volNum;
    }

    public void setVolNum(float volNum) {
        this.volNum = volNum;
    }

    public String getFirm() {
        return firm;
    }

    public void setFirm(String firm) {
        this.firm = firm;
    }

    public String getFirmCountry() {
        return firmCountry;
    }

    public void setFirmCountry(String firmCountry) {
        this.firmCountry = firmCountry;
    }

    public String getPack() {
        return pack;
    }

    public void setPack(String pack) {
        this.pack = pack;
    }

    public String getPackFirm() {
        return packFirm;
    }

    public void setPackFirm(String packFirm) {
        this.packFirm = packFirm;
    }

    public String getPackFirmCountry() {
        return packFirmCountry;
    }

    public void setPackFirmCountry(String packFirmCountry) {
        this.packFirmCountry = packFirmCountry;
    }

    public String getCompl() {
        return compl;
    }

    public void setCompl(String compl) {
        this.compl = compl;
    }

    public boolean isFlagKek() {
        return flagKek;
    }

    public void setFlagKek(boolean flagKek) {
        this.flagKek = flagKek;
    }

    public boolean isFlagJVNLS() {
        return flagJVNLS;
    }

    public void setFlagJVNLS(boolean flagJVNLS) {
        this.flagJVNLS = flagJVNLS;
    }

    public boolean isForbid() {
        return forbid;
    }

    public void setForbid(boolean forbid) {
        this.forbid = forbid;
    }

    public Date getDateInclude() {
        return dateInclude;
    }

    public void setDateInclude(Date dateInclude) {
        this.dateInclude = dateInclude;
    }

    public Date getDateExclude() {
        return dateExclude;
    }

    public void setDateExclude(Date dateExclude) {
        this.dateExclude = dateExclude;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDocuments() {
        return documents;
    }

    public void setDocuments(String documents) {
        this.documents = documents;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public float getWeightNum() {
        return weightNum;
    }

    public void setWeightNum(float weightNum) {
        this.weightNum = weightNum;
    }

    public FarmGroup getFarmGroup() {
        return farmGroup;
    }

    public void setFarmGroup(FarmGroup farmGroup) {
        this.farmGroup = farmGroup;
    }
}
