package com.annuaire.contratservice.dto;
import com.annuaire.contratservice.ennumeration.Etat;
import com.annuaire.contratservice.ennumeration.Status;
import com.annuaire.contratservice.model.*;
import lombok.*;

/**
 * @author karim hmadi

 * @description structure dto DTO

 */

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NodeDto {

    private String id;
    private int key;
    private int treeId;
    private int nodeId;
    private int orgId;
    private String parent;
    private String StructureId;
    private Fournisseur fournisseur;
    private String reference;
    private String objet;
    private Status status;
    private Etat etat;
    private TypeContrat typecontrat;
    private NatureContrat naturecontrat;
    private DateContrat date_debut;
    private DateContrat date_fin;
    private int duree_annuel;
    private int echeance;
    private boolean isnode;
    private String createdBy;
    private String updatedBy;
}
