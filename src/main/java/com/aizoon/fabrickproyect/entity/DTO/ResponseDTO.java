package com.aizoon.fabrickproyect.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String status;
    private List<ResponseDTO.Error> error;
    private List<ResponseDTO.Error> errors;
    private Object payload;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Error {
        private String code;
        private String description;
        private String params;
}}
