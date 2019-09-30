package com.chisw.banking.service.mapper;

import com.chisw.banking.domain.Operation;
import com.chisw.banking.service.dto.OperationDto;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {
    public Operation OperationDtoToOperation(OperationDto source) {
        Operation operation = new Operation();
        operation.setActionWithBalance(source.getActionWithBalance());
        operation.setOperation(source.getOperation());
        operation.setOperationInfo(source.getOperationInfo());
        return operation;
    }
}
