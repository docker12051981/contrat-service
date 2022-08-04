package com.annuaire.contratservice.dto;

import com.annuaire.contratservice.model.TypeContrat;
import lombok.*;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NatureContratDto {

    private String id;
    private String lib_fr;
    private TypeContrat typecontrat;
}
