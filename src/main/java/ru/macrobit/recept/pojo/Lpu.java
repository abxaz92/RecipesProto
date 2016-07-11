package ru.macrobit.recept.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.macrobit.recept.abstracts.EntityInterface;
import ru.macrobit.recept.commons.Recept;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by david on 7/8/16.
 */
@Entity
public class Lpu implements EntityInterface {
    @Id
    private Long id;
    private String name;
    private String OGRN;
    private String INN;
    private String OKATO;
    private String codeTFOMS;
    private String index;
    private String address;
    private String fullName;
    private String phone;
    private String codeLPU;
    private Long dateB;
    private Long dateE;
    private String headDocSecondName;
    private String headDocName;
    private String headDocMiddleName;
    private String accountantName;
    private String accountantSecondName;
    private String accountantMiddleName;
    private String email;
    private String fax;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOGRN() {
        return OGRN;
    }

    public void setOGRN(String oGRN) {
        OGRN = oGRN;
    }

    public String getInn() {
        return INN;
    }

    public void setInn(String inn) {
        INN = inn;
    }

    public String getOKATO() {
        return OKATO;
    }

    public void setOKATO(String oKATO) {
        OKATO = oKATO;
    }

    public String getCodeTFOMS() {
        return codeTFOMS;
    }

    public void setCodeTFOMS(String codeTFOMS) {
        this.codeTFOMS = codeTFOMS;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodeLPU() {
        return codeLPU;
    }

    public void setCodeLPU(String codeLPU) {
        this.codeLPU = codeLPU;
    }

    public String getHeadDocSecondName() {
        return headDocSecondName;
    }

    public void setHeadDocSecondName(String headDocSecondName) {
        this.headDocSecondName = headDocSecondName;
    }

    public String getHeadDocName() {
        return headDocName;
    }

    public void setHeadDocName(String headDocName) {
        this.headDocName = headDocName;
    }

    public String getHeadDocMiddleName() {
        return headDocMiddleName;
    }

    public void setHeadDocMiddleName(String headDocMiddleName) {
        this.headDocMiddleName = headDocMiddleName;
    }

    public String getAccountantName() {
        return accountantName;
    }

    public void setAccountantName(String accountantName) {
        this.accountantName = accountantName;
    }

    public String getAccountantSecondName() {
        return accountantSecondName;
    }

    public void setAccountantSecondName(String accountantSecondName) {
        this.accountantSecondName = accountantSecondName;
    }

    public String getAccountantMiddleName() {
        return accountantMiddleName;
    }

    public void setAccountantMiddleName(String accountantMiddleName) {
        this.accountantMiddleName = accountantMiddleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public Long getDateB() {
        return dateB;
    }

    public void setDateB(Long dateB) {
        this.dateB = dateB;
    }

    public Long getDateE() {
        return dateE;
    }

    public void setDateE(Long dateE) {
        this.dateE = dateE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        try {
            return Recept.MAPPER.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
