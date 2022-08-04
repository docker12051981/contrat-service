package com.annuaire.contratservice.repository;

import com.annuaire.contratservice.model.TypeContrat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author karim hmadi

 * @description repository for type contrat

 */
@Repository
public interface ITypeContratRepository extends MongoRepository<TypeContrat,String> {
}
