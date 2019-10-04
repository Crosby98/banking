package com.chisw.banking.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACCOUNT", schema = "BANKING")
@Data
public class Account implements Serializable {

    private static final long serialVersionUID = 4695283171881691687L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT")
    @SequenceGenerator(name = "seq_account", sequenceName = "SEQ_ACCOUNT", schema = "BANKING", initialValue = 1, allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "balance", nullable = false)
    private Double balance;

    @OneToMany(
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    @JoinColumn(name = "operations_id", foreignKey = @ForeignKey(name = "fk_banking_operations"))
    private List<Operation> accountOperations = new ArrayList<>();

    @Column(name = "createDate", nullable = false)
    private Date createDate;

    @Column(name = "status")
    @Enumerated
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        return id != null && id.equals(((Account) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public static Account createNewActiveAccount() {
        Account account = new Account();
        account.setBalance(0.0);
        account.setCreateDate(new Date());
        account.setStatus(Status.ACTIVE);
        return account;
    }
}
