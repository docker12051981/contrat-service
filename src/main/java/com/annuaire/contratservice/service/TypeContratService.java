package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.TypeContratDto;
import com.annuaire.contratservice.model.TypeContrat;
import com.annuaire.contratservice.repository.ITypeContratRepository;
import com.annuaire.contratservice.tools.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TypeContratService implements  ITypeContratService{
    private final ITypeContratRepository typeContratRepository;
    public TypeContratService(ITypeContratRepository typeContratRepository) {
        super();
        this.typeContratRepository = typeContratRepository;
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;

    @Override
    public TypeContrat saveTypeContrat(TypeContratDto typeContratDto)
    {
        TypeContrat typecontrat = converter.typedtoToEntity(typeContratDto);

        return typeContratRepository.save(typecontrat);
    }

    @Override
    public List<TypeContrat> findAllTypeContrats()
    {
        return typeContratRepository.findAll();
    }

    @Override
    public TypeContrat findTypeContratById(String id) {
        Optional<TypeContrat> result = typeContratRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new InvalidConfigurationPropertyValueException("TypeContrat", "id", id);
        }

    }

    @Override
    public TypeContrat updateTypeContrat(String id, TypeContrat contratRequest) {
        TypeContrat typecontrat = typeContratRepository.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("TypeContrat", "id", id));
        typecontrat.setLib_fr(contratRequest.getLib_fr());
        return typeContratRepository.save(typecontrat);
    }

    @Override
    public Boolean deleteTypeContrat(String id) {
        typeContratRepository.deleteById(id);
        return true;
    }
}
