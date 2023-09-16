package com.ledger.es_test1.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@TableName("users")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId
    private String id;
    private String username;
    private String password;
    private String role;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
