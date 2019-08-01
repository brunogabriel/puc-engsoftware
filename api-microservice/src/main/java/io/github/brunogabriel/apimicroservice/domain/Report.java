package io.github.brunogabriel.apimicroservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by brunogabriel on 2019-07-18.
 */
@Data
@EqualsAndHashCode
@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public String employee;
    public String clientName;
    public String petName;
    public String type;
    public Integer duration;
    public Double price;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "America/Sao_Paulo")
    public Date date;

    public String getType() {
        return type;
    }
}
