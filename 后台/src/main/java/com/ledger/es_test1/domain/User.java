package com.ledger.es_test1.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

@Data
public class User {
    private int id;
    private String username;
    private String profileImageUrl;
    private String description;
}
