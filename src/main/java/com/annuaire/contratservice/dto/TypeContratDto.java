package com.annuaire.contratservice.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TypeContratDto {
    private String id;
    private String lib_fr;
}
