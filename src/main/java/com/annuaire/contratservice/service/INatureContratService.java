package com.annuaire.contratservice.service;
import com.annuaire.contratservice.dto.NatureContratDto;
import com.annuaire.contratservice.model.NatureContrat;
import java.util.List;

public interface INatureContratService {
    NatureContrat saveNatureContrat(NatureContratDto naturecontratDto);
    List<NatureContrat> findAllNatureContrats();
    List<NatureContrat> findNatureContratByType(String id);
    NatureContrat findNatureContratById(String id);
    NatureContrat updateNatureContrat(String id, NatureContrat contratRequest);
    Boolean deleteNatureContrat(String id);
}
