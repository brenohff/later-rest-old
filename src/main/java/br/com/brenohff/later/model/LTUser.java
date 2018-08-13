package br.com.brenohff.later.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "USERS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTUser implements Serializable {

    private static final long serialVersionUID = 9077168091743018235L;

    @Id
    private String id;

    @Column(unique = true)
    private String email;

    @Setter
    @OneToMany(mappedBy = "users")
    private Set<LTEvent> events;

    @Setter
    @OneToMany(mappedBy = "users")
    private Set<LTChat> comments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date member_since;

    private String name;
    private String birthday;
    private String gender;
    private String link;
    private String image;
    private String image_long;

    @JsonIgnore
    public Set<LTEvent> getEvents() {
        return events;
    }

    @JsonIgnore
    public Set<LTChat> getComments() {
        return comments;
    }

}
