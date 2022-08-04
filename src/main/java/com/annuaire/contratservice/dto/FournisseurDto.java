package com.annuaire.contratservice.dto;

import com.annuaire.contratservice.model.Responsable;
import lombok.*;


@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurDto {

    private String id;
    private String organisme;
    private String raison_social;
    private String matricule_fiscale;
    private String email;
    private String adresse;
    private String num_tel;
    private String num_fax;
    private Responsable responsable;
}
