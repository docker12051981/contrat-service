package com.annuaire.contratservice.model;

import com.annuaire.contratservice.ennumeration.Etat;
import com.annuaire.contratservice.ennumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * @author karim hmadi

 * @description entity contrat

 */

@Document(collection = "contrat")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contrat {
    @Id
    private String id;
    @Transient
    public static final String SEQUENCE_NAME = "contrat_sequence";
    @Indexed(unique = true)
    private int key;
    private int tree;
    private int nodeId;
    private int orgId;
    private String parent;
    private String StructureId;
    @Field("fournisseur")
    @DBRef
    private Fournisseur fournisseur;
    @NotNull
    private String reference;
    @NotNull
    private String objet;
    @Field("status")
    private Status status;
    @Field("etat")
    private Etat etat;
    @Field("typecontrat")
    @DBRef
    private TypeContrat typecontrat;
    @Field("naturecontrat")
    @DBRef
    private NatureContrat naturecontrat;
    private DateContrat date_debut;
    private DateContrat date_fin;
    private int duree_annuel;
    private int echeance;
    private boolean isnode;
    private String createdBy;
    private String updatedBy;
    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date lastModifiedTime;
}
