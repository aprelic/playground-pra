/*
 * Copyright Avaloq Licence AG. All rights reserved.
 * Avaloq Licence AG
 * Schwerzistrasse 6 | CH-8807 Freienbach | Switzerland
 *
 * This software is the confidential and proprietary information of Avaloq Licence AG.
 * You shall not disclose whole or parts of it and shall use it only in accordance with the terms
 * of the license agreement you entered into with Avaloq Licence AG.
 */

package com.avaloq.avaloqpra.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
//@Table(name = "PRA_ECL_CNTPRTY")
public class Counterparty {

    @OneToMany
    @JoinColumn(name = "counterparty_id", referencedColumnName = "id")
    List<Position> positions;
    @Id
    @NotNull
    @GeneratedValue
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pra_ecl_cntprty_seq") // sequence must manually generated
    //@SequenceGenerator(name = "pra_ecl_cntprty_seq", sequenceName = "pra_ecl_cntprty_seq", allocationSize = 100) // dflt value is 50
    private Long id;
    @NotNull
    //@Column(name = "avq_key")
    private Long avaloqKey;
    @NotNull
    @Size(max = 100)
    //@Column(name = "sym_key")
    private String symbolicKey;
    @NotNull
    @Size(max = 500)
    private String name;

    protected Counterparty() { // JPA
    }

    public Counterparty(@NotNull Long avaloqKey,
                        @NotNull @Size(max = 100) String symbolicKey,
                        @NotNull @Size(max = 500) String name) {
        this.avaloqKey = avaloqKey;
        this.symbolicKey = symbolicKey;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Counterparty that = (Counterparty) o;
        return Objects.equals(avaloqKey, that.avaloqKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(avaloqKey);
    }

    @Override
    public String toString() {
        return "Counterparty{" +
                "id=" + id +
                ", avaloqKey=" + avaloqKey +
                ", symbolicKey='" + symbolicKey + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Long getAvaloqKey() {
        return avaloqKey;
    }

    public String getSymbolicKey() {
        return symbolicKey;
    }

    public String getName() {
        return name;
    }
}
