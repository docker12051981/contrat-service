package com.annuaire.contratservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomContratDto {
    private String id;
    private int key;
    private String reference;
    private String objet;
}
