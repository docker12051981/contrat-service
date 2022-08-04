package com.annuaire.contratservice.repository;

import com.annuaire.contratservice.model.Fournisseur;
import com.annuaire.contratservice.model.NatureContrat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFournisseurRepository extends MongoRepository<Fournisseur,String> {
    Optional<Fournisseur> findById(String id);

    List<Fournisseur> findByOrganisme(String organisme);
}
