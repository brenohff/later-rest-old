package br.com.brenohff.later.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LTCategoryEventPK implements Serializable {

    private static final long serialVersionUID = -5700333885181180979L;

    private Long category_id;
    private Long event_id;

}
