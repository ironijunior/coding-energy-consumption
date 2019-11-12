package com.ironijunior.energyconsumption.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Class that represents the Counter table.
 *
 * @author Ironi Junior Medina
 */
@Data
@NoArgsConstructor
@Entity
public class CounterEntity {

    @Id
    private String id;
    @NotNull
    @Column
    private String name;
}
