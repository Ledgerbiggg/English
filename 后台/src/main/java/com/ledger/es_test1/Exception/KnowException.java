package com.ledger.es_test1.Exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author ledger
 * @version 1.0
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KnowException extends RuntimeException{
    private String message;
    private Integer code;
    public KnowException(String message) {
        this.message = message;
    }

}
