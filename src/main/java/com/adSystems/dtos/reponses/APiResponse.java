package com.adSystems.dtos.reponses;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class APiResponse {
    private boolean isSuccessful;
    private Object data;
}
