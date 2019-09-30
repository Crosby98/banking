package com.chisw.banking.service.dto;

import com.chisw.banking.domain.Account;
import com.chisw.banking.domain.Status;
import com.chisw.banking.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NonNull
    @Size(max = 50)
    private String firstName;

    @NonNull
    @Size(max = 50)
    private String surName;

    @NonNull
    @Size(min = 8, max = 254)
    private String password;

    @NonNull
    @Size(max = 50)
    private String phone;

    @NonNull
    @Email
    @Size(min = 5, max = 254)
    private String email;

    private Status status;

    private Account account;

    public UserDTO(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.firstName = user.getFirstName();
        this.phone = user.getSurName();
        this.email = user.getEmail();
        this.status = Status.valueOf(user.getStatus());
        this.account = user.getAccount();
    }

    @Override
    public String toString() {
        return "UserDTO{" +
            "firstName='" + firstName + '\'' +
            ", surName='" + surName + '\'' +
            ", email='" + email + '\'' +
            ", phone='" + phone + '\'' +
            ", status='" + status.getValue() + '\'' +
            "}";
    }
}
