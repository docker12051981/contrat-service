package com.annuaire.contratservice.object;

import com.annuaire.contratservice.ennumeration.Etat;
import com.annuaire.contratservice.ennumeration.Status;
import com.annuaire.contratservice.model.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.javers.core.metamodel.annotation.DiffIgnore;
import org.javers.core.metamodel.annotation.TypeName;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author karim hmadi

 * @description value object for recursive structure

 */

@TypeName("ContratNode")
@Getter
@Setter
@ToString
public class ContratNode {

    @Id
    private String id;
    private int key;
    @DiffIgnore
    private int treeId;
    private int nodeId;
    private int orgId;
    @DiffIgnore
    private List<Integer> parentId;
    private List<ContratNode> children;
    @DiffIgnore
    @JsonIgnore
    private List<ContratNode> parents;
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

    public ContratNode() {
        this.parentId = new ArrayList<>();
        this.children = new ArrayList<>();
        this.parents = new ArrayList<>();
    }

    // TODO: throws java.lang.NullPointerException: null and in many cases hierarchies are not fetched correctly
    public void addChild(ContratNode child) {
        if (this.children!= null && !this.children.contains(child) && child != null)
            this.children.add(child);
    }

    public void addParent(ContratNode parent) {
        if (this.parents!= null && !this.parents.contains(parent) && parent != null)
            this.parents.add(parent);
    }
}
