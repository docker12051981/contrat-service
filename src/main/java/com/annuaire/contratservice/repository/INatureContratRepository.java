package com.annuaire.contratservice.repository;

import com.annuaire.contratservice.model.NatureContrat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface INatureContratRepository extends MongoRepository<NatureContrat,String> {
    Optional<NatureContrat> findById(String id);
    @Query(value="{ 'type.id': ?0 }, Fields: null, Sort: null")
    List<NatureContrat> findByType(String id);
}
