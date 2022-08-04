package com.annuaire.contratservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "contrat")
public class CustomContrat {
    private String id;
    private int key;
    private String reference;
    private String objet;
}
