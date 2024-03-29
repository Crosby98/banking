package com.chisw.banking.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "OPERATION", schema = "BANKING")
@Data
public class Operation implements Serializable {

    private static final long serialVersionUID = 6914296386360467585L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_OPERATION")
    @SequenceGenerator(name = "seq_operation", sequenceName = "SEQ_OPERATION", schema = "BANKING", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "operation", nullable = false)
    @Enumerated(EnumType.STRING)
    private OperationType operation;

    @NotNull
    @Column(name = "action_with_balance", nullable = false)
    private Double actionWithBalance;

    @Column(name = "operation_info", nullable = false)
    private String operationInfo;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "balance", nullable = false)
    private OperationStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Operation)) {
            return false;
        }
        return id != null && id.equals(((Operation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
