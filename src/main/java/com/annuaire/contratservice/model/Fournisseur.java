package com.annuaire.contratservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "fournisseur")
public class Fournisseur {
    @Id
    private String id;
    private String organisme;
    @NotNull
    @Indexed(unique = true)
    private String raison_social;
    @Indexed(unique = true)
    private String matricule_fiscale;
    @Email
    private String email;
    private String adresse;
    private String num_tel;
    private String num_fax;
    private Responsable responsable;
    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date lastModifiedTime;
}
