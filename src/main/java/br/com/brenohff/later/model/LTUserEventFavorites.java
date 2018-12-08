package br.com.brenohff.later.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by breno.franco on 07/12/2018
 */

@Getter
@Setter
@Entity
@Table(name = "USER_EVENT_FAVORITES")
@IdClass(value = LTUserEventPK.class)
public class LTUserEventFavorites implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long event_id;

    @Id
    private String user_id;

    public LTUserEventFavorites(Long event_id, String user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }

    public LTUserEventFavorites() {
    }
}