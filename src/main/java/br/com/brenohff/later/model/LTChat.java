package br.com.brenohff.later.model;

import br.com.brenohff.later.enums.MessageType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "CHAT")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LTChat implements Serializable {

    private static final long serialVersionUID = 3625234538742558082L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "users_id", nullable = false)
    private LTUser users;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dtPost;

    private MessageType type;
    private String content;
    private String eventId;

}
