package com.auth.authentication.dto.response;

import com.auth.authentication.entity.Account;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountResponse {
    private Long id;
    private String name;

    public static AccountResponse toAccountResponse(Account account){
        return AccountResponse.builder()
                                .id(account.getId())
                                .name(account.getUserName())
                                .build();
    }
}
