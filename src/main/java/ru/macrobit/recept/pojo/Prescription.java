package ru.macrobit.recept.pojo;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.entities.PrescriptionInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by david on 08.07.16.
 */
@Entity
@TypeDefs({@TypeDef(name = "PrescriptionInfoObject", typeClass = PrescriptionInfo.class)})
public class Prescription implements EntityInterface {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String sourceFunding; // источник финансирования (принимает следующие значения: 1. Федеральный бюджет, 2. Региональный бюджет, 3. Муниципальный бюджет)
    private Double percentagePayment;  // % оплаты из источника финансирования (может принмать следующие значения: 50% и 100%)
    private String validity; // Срок действия рецепта (может принимать значения: 1 месяц, 2 месяца, 3 месяца)
    private String series; // серия рецепта
    private String number; // Номер рецепта
    private long issuedate; // Дата выписки рецепта
    private Date date;
    private long dateCreation;
    private Long lpuId; // ЛПУ
    private String notification; //Информация о приеме
    private String signsale; // Признак отоваренности рецепта
    private String status; // статус рецепта
    private boolean archive; // Архив
    private float num; //Кол. приемов в день
    private float taking; //За 1 прием
    private float duration; //Продолжительность
    private String signa; //Signa
    private String form; //форма рецепта
    @Type(type = "PrescriptionInfoObject")
    private PrescriptionInfo info;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSourceFunding() {
        return sourceFunding;
    }

    public void setSourceFunding(String sourceFunding) {
        this.sourceFunding = sourceFunding;
    }

    public Double getPercentagePayment() {
        return percentagePayment;
    }

    public void setPercentagePayment(Double percentagePayment) {
        this.percentagePayment = percentagePayment;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
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

    public long getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(long issuedate) {
        this.issuedate = issuedate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(long dateCreation) {
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

    public String getSignsale() {
        return signsale;
    }

    public void setSignsale(String signsale) {
        this.signsale = signsale;
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
}
