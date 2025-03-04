package fr.dawan.spring_rest_api.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@MappedSuperclass // annotation qui ne génère pas de table en base - mais qui permet aux classes de récupèrer ses attributs
public class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Version
    private int version;
}
