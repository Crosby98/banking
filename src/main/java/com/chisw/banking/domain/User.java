package com.chisw.banking.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "USERS", schema = "BANKING")
@Data
public class User implements Serializable {

    private static final long serialVersionUID = -4220669447462881674L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER")
    @SequenceGenerator(name = "seq_user", sequenceName = "SEQ_USER", schema = "BANKING", initialValue = 1, allocationSize = 1)
    private Long id;

    @JsonIgnore
    @NotNull
    @Size(min = 8, max = 254)
    @Column(name = "password", nullable = false)
    private String password;

    @Size(max = 50)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "SURNAME", nullable = false)
    private String surName;

    @Email
    @Size(max = 254)
    @Column(name = "EMAIL", unique = true, nullable = false)
    private String email;

    @OneToOne(
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "fk_banking_account"))
    private Account account;

    @Size(max = 50)
    @Column(name = "PHONE", unique = true, nullable = false)
    private String phone;

    @Column(name = "STATUS")
    private String status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return id != null && id.equals(((User) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "User{" +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + surName + '\'' +
            ", phone='" + phone + '\'' +
            ", email='" + email + '\'' +
            "}";
    }
}
