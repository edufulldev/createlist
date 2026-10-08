package edu.dev.createlist.infrastructure.persistence.h2;

import jakarta.persistence.*;

@Entity
@Table(name = "config_properties")
@Access(AccessType.FIELD)
public class ConfigPropertyEntityH2 {

    @Id
    @Column(name = "name_key")
    private String nameKey;

    @Column(name = "value_key")
    private String valueKey;

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getValueKey() {
        return valueKey;
    }

    public void setValueKey(String valueKey) {
        this.valueKey = valueKey;
    }
}
