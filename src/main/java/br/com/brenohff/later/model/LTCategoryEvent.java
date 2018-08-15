package br.com.brenohff.later.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "CATEGORY_EVENT")
@IdClass(value = LTCategoryEventPK.class)
public class LTCategoryEvent implements Serializable {

    private static final long serialVersionUID = 2334421866476625056L;

    @Id
    private Long category_id;

    @Id
    private Long event_id;

    public LTCategoryEvent() {
    }

    public LTCategoryEvent(Long category_id, Long event_id) {
        super();
        this.category_id = category_id;
        this.event_id = event_id;
    }
}
