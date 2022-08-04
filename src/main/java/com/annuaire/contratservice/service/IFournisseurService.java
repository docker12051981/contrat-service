package com.annuaire.contratservice.service;
import com.annuaire.contratservice.dto.FournisseurDto;
import com.annuaire.contratservice.model.Fournisseur;

import java.util.List;

public interface IFournisseurService {
    Fournisseur saveFournisseur(FournisseurDto fournisseurDto);
    List<Fournisseur> findAllFournisseurs();
    List<Fournisseur> findFournisseurByOrganisme(String organisme);
    Fournisseur findFournisseurById(String id);
    Fournisseur updateFournisseur(String id, Fournisseur fournisseurRequest);
    Boolean deleteFournisseur(String id);
}
