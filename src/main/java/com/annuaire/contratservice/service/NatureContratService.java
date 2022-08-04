package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.NatureContratDto;
import com.annuaire.contratservice.model.NatureContrat;
import com.annuaire.contratservice.repository.INatureContratRepository;
import com.annuaire.contratservice.tools.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NatureContratService implements INatureContratService{

    private final INatureContratRepository natureContratRepository;
    public NatureContratService(INatureContratRepository natureContratRepository) {
        super();
        this.natureContratRepository = natureContratRepository;
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;

    @Override
    public NatureContrat saveNatureContrat(NatureContratDto naturecontratDto)
    {

        NatureContrat naturecontrat = converter.naturedtoToEntity(naturecontratDto);

        return natureContratRepository.save(naturecontrat);
    }

/*
    @Override
    public List<NatureContrat> findFonctionnaireByStructure(String idstructure)
    {
        return fonctionnaireRepository.findByFonctionnaireAsCustom(idstructure);
    }
*/


    @Override
    public NatureContrat findNatureContratById(String id) {
        Optional<NatureContrat> result = natureContratRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new InvalidConfigurationPropertyValueException("NatureContrat", "id", id);
        }
    }


    @Override
    public List<NatureContrat> findNatureContratByType(String id) {
        List<NatureContrat> result = natureContratRepository.findByType(id);
            return result;
    }


    @Override
    public Boolean deleteNatureContrat(String id) {
        natureContratRepository.deleteById(id);
        return true;
    }

    @Override
    public List<NatureContrat> findAllNatureContrats()
    {
        return natureContratRepository.findAll();
    }

    @Override
    public NatureContrat updateNatureContrat(String id, NatureContrat naturecontratRequest) {
        NatureContrat natureContrat = natureContratRepository.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("NatureContrat", "id", id));
        natureContrat.setLib_fr(naturecontratRequest.getLib_fr());
        natureContrat.setTypecontrat(naturecontratRequest.getTypecontrat());

        return natureContratRepository.save(natureContrat);
    }


}
