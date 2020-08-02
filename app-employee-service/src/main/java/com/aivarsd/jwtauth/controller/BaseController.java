package com.aivarsd.jwtauth.controller;

import com.aivarsd.jwtauth.dtos.response.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */

@Slf4j
public abstract class BaseController {

    abstract String getApiType();

    protected ResponseEntity<Object> getResponseEntityError(HttpStatus httpStatus, String errMsg)
    {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(
                httpStatus,
                getApiType(),
                errMsg);

        log.info("Error : \n  " + apiErrorResponse);
        return new ResponseEntity<Object>( apiErrorResponse, new HttpHeaders(), apiErrorResponse.getStatus());
    }
}
