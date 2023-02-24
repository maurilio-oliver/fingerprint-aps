package org.example.model;

import java.time.LocalDateTime;
import java.util.Map;

public class FingerPrint {
    private Long id;
    private Long personId;
    private String fingerPrint;
    private Map<String, LocalDateTime> lote;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(String fingerPrint) {
        this.fingerPrint = fingerPrint;
    }

    public Map<String, LocalDateTime> getLote() {
        return lote;
    }

    public void setLote(Map<String, LocalDateTime> lote) {
        this.lote = lote;
    }
}
