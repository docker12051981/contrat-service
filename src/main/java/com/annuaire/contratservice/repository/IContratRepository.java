package com.annuaire.contratservice.repository;

import com.annuaire.contratservice.model.Contrat;
import com.annuaire.contratservice.model.CustomContrat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IContratRepository extends MongoRepository<Contrat,String> {
    @Query(value="{ 'fournisseur.raison_social': ?0 }, Fields: null, Sort: null")
    List<Contrat> findByFournisseurAsCustom(String fournisseur);

    @Query(value="{ 'key' : ?0 }",fields="{ 'id' : 1, 'key' : 1, 'refernce' : 1, 'objet' : 1}")
    CustomContrat findByContratAsCustom(Integer key);

    Optional<Contrat> findByKey(Integer id);

    List<Contrat> findByParent(String id);

    List<Contrat> findContratByOrgId(Integer organisme);
}
