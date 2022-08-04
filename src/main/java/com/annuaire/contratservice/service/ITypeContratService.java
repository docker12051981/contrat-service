package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.TypeContratDto;
import com.annuaire.contratservice.model.TypeContrat;

import java.util.List;
/**
 * @author karim hmadi

 * @description interface service type contrat

 */
public interface ITypeContratService {
    TypeContrat saveTypeContrat(TypeContratDto typecontratDto);
    List<TypeContrat> findAllTypeContrats();
    TypeContrat findTypeContratById(String id);
    TypeContrat updateTypeContrat(String id, TypeContrat contratRequest);
    Boolean deleteTypeContrat(String id);
}
