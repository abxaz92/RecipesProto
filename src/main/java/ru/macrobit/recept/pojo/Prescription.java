package ru.macrobit.recept.pojo;

import org.hibernate.annotations.*;
import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.entities.PrescriptionInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * Created by david on 08.07.16.
 */
@Entity
@TypeDefs({@TypeDef(name = "PrescriptionInfoObject", typeClass = PrescriptionInfo.class)})
public class Prescription implements EntityInterface {
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "prescription_id_seq"))
    @Id
    @GeneratedValue(generator = "generator")
    private Long id;

    /**
     * источник финансирования (принимает следующие значения: 1. Федеральный бюджет, 2. Региональный бюджет, 3. Муниципальный бюджет)
     */
    @Min(1)
    @Max(3)
    private int sourceFunding;

    @Max(100)
    @Min(0)
    private double percentagePayment;  // % оплаты из источника финансирования (может принмать следующие значения: 50% и 100%)

    /**
     * Срок действия рецепта (может принимать значения: 1 месяц, 2 месяца, 3 месяца)
     */
    @Min(1)
    private int validity;
    private String series; // серия рецепта
    private String number; // Номер рецепта

    private Date date;
    private Date dateCreation;
    private Long lpuId; // ЛПУ
    private String notification; //Информация о приеме
    private boolean signsale; // Признак отоваренности рецепта
    private String status; // статус рецепта
    private boolean archive; // Архив
    private float num; //Кол. приемов в день
    private float taking; //За 1 прием
    private float duration; //Продолжительность
    private String signa; //Signa
    private String form; //форма рецепта
    @Type(type = "PrescriptionInfoObject")
    private PrescriptionInfo info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSourceFunding() {
        return sourceFunding;
    }

    public void setSourceFunding(int sourceFunding) {
        this.sourceFunding = sourceFunding;
    }

    public double getPercentagePayment() {
        return percentagePayment;
    }

    public void setPercentagePayment(double percentagePayment) {
        this.percentagePayment = percentagePayment;
    }

    public int getValidity() {
        return validity;
    }

    public void setValidity(int validity) {
        this.validity = validity;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getLpuId() {
        return lpuId;
    }

    public void setLpuId(Long lpuId) {
        this.lpuId = lpuId;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public float getNum() {
        return num;
    }

    public void setNum(float num) {
        this.num = num;
    }

    public float getTaking() {
        return taking;
    }

    public void setTaking(float taking) {
        this.taking = taking;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getSigna() {
        return signa;
    }

    public void setSigna(String signa) {
        this.signa = signa;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public PrescriptionInfo getInfo() {
        return info;
    }

    public void setInfo(PrescriptionInfo info) {
        this.info = info;
    }

    public boolean isSignsale() {
        return signsale;
    }

    public void setSignsale(boolean signsale) {
        this.signsale = signsale;
    }
}
