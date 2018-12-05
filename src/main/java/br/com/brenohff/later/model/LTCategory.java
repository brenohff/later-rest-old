package br.com.brenohff.later.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTCategory implements Serializable {

    private static final long serialVersionUID = 7158436812533878831L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "category_event", joinColumns = {@JoinColumn(name = "category_id")}, inverseJoinColumns = {
            @JoinColumn(name = "event_id")})

    @Setter
    private Set<LTEvent> events = new HashSet<>();

    private String url;
    private String name;
    private String baseColor;
    private String baseColor700;

    public LTCategory(String url, String name, String baseColor, String baseColor700) {
        this.url = url;
        this.name = name;
        this.baseColor = baseColor;
        this.baseColor700 = baseColor700;
    }

    @JsonIgnore
    public Set<LTEvent> getEvents() {
        return events;
    }

}