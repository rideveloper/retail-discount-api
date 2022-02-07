package com.ridwan.retaildiscountapi.exception;

import com.ridwan.retaildiscountapi.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * @author Ridwan Mustapha
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private String message;
    private HttpStatus httpStatus;
    private Status status;
}
