package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.ContratDto;
import com.annuaire.contratservice.model.Contrat;
import com.annuaire.contratservice.model.CustomContrat;
import com.annuaire.contratservice.repository.IContratRepository;
import com.annuaire.contratservice.tools.Converter;
import com.thoughtworks.xstream.core.SequenceGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.annuaire.contratservice.model.Contrat.SEQUENCE_NAME;

/**
 * @author karim hmadi

 * @description interface service contrat

 */

@Service
public class ContratService implements IContratService {
    private final IContratRepository contratRepository;
    public ContratService (IContratRepository contratRepository) {
        super();
        this.contratRepository = contratRepository;
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    Converter converter;
    @Autowired
    private sequencegen service;

    @Override
    public Contrat saveContrat(ContratDto contratDto)
    {
        Contrat contrat = converter.contratdtoToEntity(contratDto);

        if(contrat.getParent()==null) {
            contrat.setTree(service.getSequencegen(SEQUENCE_NAME));
        }
        return contratRepository.save(contrat);
    }

    @Override
    public List<Contrat> findContratByFournisseur(String fournisseur)
    {
        return contratRepository.findByFournisseurAsCustom(fournisseur);
    }

    @Override
    public List<Contrat> findContratByOrgId(Integer organisme)
    {
        return contratRepository.findContratByOrgId(organisme);
    }

    @Override
    public CustomContrat findContratByCustom(Integer key)
    {
        return contratRepository.findByContratAsCustom(key);
    }

    @Override
    public Contrat findContratById(String id) {
        Optional<Contrat> result = contratRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new InvalidConfigurationPropertyValueException("Contrat", "id", id);
        }

    }


    @Override
    public Contrat findContratByKey(Integer id) {
        Optional<Contrat> result = contratRepository.findByKey(id);
        if(result.isPresent()) {
            return result.get();
        }else {
            throw new InvalidConfigurationPropertyValueException("Structure", "key", "invalid key");
        }
    }


    @Override
    public List<Contrat> findContratsByParent(String id)
    {
        return contratRepository.findByParent(id);
    }

    @Override
    public Boolean deleteContrat(String id) {
        contratRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Contrat> findAllContrats()
    {
        return contratRepository.findAll();
    }


   @Override
    public Contrat updateContrat(String id, Contrat contratRequest) {
        Contrat contrat = contratRepository.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Contrat", "id", id));

        contrat.setParent(contratRequest.getParent());
        contrat.setTree(contrat.getTree());
        contrat.setParent(contrat.getParent());
        contrat.setReference(contrat.getReference());
        contrat.setObjet(contrat.getObjet());
        contrat.setStatus(contrat.getStatus());
        contrat.setEtat(contrat.getEtat());
        contrat.setTypecontrat(contrat.getTypecontrat());
        contrat.setNaturecontrat(contrat.getNaturecontrat());
        contrat.setDate_debut(contrat.getDate_debut());
        contrat.setDate_fin(contrat.getDate_fin());
        contrat.setDuree_annuel(contrat.getDuree_annuel());
        contrat.setEcheance(contrat.getEcheance());
        contrat.setIsnode(contrat.isIsnode());
        contrat.setStatus(contrat.getStatus());
        contrat.setCreatedBy(contrat.getCreatedBy());

        return contratRepository.save(contrat);
    }
}
