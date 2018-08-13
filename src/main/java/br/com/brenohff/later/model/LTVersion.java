package br.com.brenohff.later.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "INFO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTVersion implements Serializable {

    private static final long serialVersionUID = 2036535387284484618L;

    @Id
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date last_update;
    private String version;
    private String author;
    private String contact;
    private boolean working;

    public LTVersion() {
    }

    public LTVersion(String version, String author, String contact, boolean working) {
        super();
        this.last_update = new Date();
        this.version = version;
        this.author = author;
        this.contact = contact;
        this.working = working;
    }
}
