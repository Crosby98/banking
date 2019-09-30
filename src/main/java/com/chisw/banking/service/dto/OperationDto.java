package com.chisw.banking.service.dto;

import com.chisw.banking.domain.OperationStatus;
import com.chisw.banking.domain.OperationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class OperationDto {

    @NonNull
    private OperationType operation;

    @NonNull
    private Double actionWithBalance;

    @NonNull
    private String operationInfo;

    private Date timestamp;

    private OperationStatus status;
}
