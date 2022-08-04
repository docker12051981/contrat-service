package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.ContratDto;
import com.annuaire.contratservice.model.Contrat;
import com.annuaire.contratservice.model.CustomContrat;

import java.util.List;

/**
 * @author karim hmadi

 * @description interface service contrat

 */
public interface IContratService {
    Contrat saveContrat(ContratDto contratDto);

    List<Contrat> findContratByFournisseur(String fournisseur);

    Contrat findContratById(String id);

    Contrat findContratByKey(Integer id);

    Boolean deleteContrat(String id);

    List<Contrat> findAllContrats();

    Contrat updateContrat(String id, Contrat contratRequest);

    List<Contrat> findContratsByParent(String id);

    CustomContrat findContratByCustom(Integer key);

    List<Contrat> findContratByOrganisme(Integer organisme);
}
