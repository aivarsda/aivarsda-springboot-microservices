package com.aivarsd.jwtauth.dtos.response;

import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Aivars Dalderis
 * @since  04.08.2020
 *
 */


@Data
public class ApiErrorResponse {

    public enum ApiType
    {
        UNKNOWN("UNKNOWN API TYPE"),
        AUTH("AUTHENTICATION API"),
        API("GENERAL API");

        private String apiType;

        ApiType(String apiType) {
            this.apiType = apiType;
        }

        public String getValue() {
            return apiType;
        }
    }

    private HttpStatus status;
    private String apiType;
    private String error;

    public ApiErrorResponse(HttpStatus status, String error) {
        super();
        this.status = status;
        this.apiType = ApiType.UNKNOWN.getValue();
        this.error = error;
    }

    public ApiErrorResponse(HttpStatus status, String apiType, String error) {
        super();
        this.status = status;
        this.apiType = apiType;
        this.error = error;
    }
}