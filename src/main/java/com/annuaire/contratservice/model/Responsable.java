package com.annuaire.contratservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Responsable {

    private String nom_resp;
    private String prenom_resp;
    @Email
    private String email_resp;
    private String num_tel_resp;
}
