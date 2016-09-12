package ru.macrobit.recept.pojo;

import ru.macrobit.recept.commons.ExemptType;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by [david] on 12.09.16.
 */
@Embeddable
public class ExemptId implements Serializable {
    private String id;
    private ExemptType type;

    public ExemptId() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExemptId exemptId = (ExemptId) o;

        if (id != null ? !id.equals(exemptId.id) : exemptId.id != null) return false;
        return type == exemptId.type;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public ExemptId(String id, ExemptType type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExemptType getType() {
        return type;
    }

    public void setType(ExemptType type) {
        this.type = type;
    }
}
