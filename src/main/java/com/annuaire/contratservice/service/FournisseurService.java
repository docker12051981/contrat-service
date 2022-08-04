package com.annuaire.contratservice.service;

import com.annuaire.contratservice.dto.FournisseurDto;
import com.annuaire.contratservice.model.Fournisseur;
import com.annuaire.contratservice.repository.IFournisseurRepository;
import com.annuaire.contratservice.tools.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FournisseurService implements IFournisseurService{

        private final IFournisseurRepository fournisseurRepository;
        public FournisseurService(IFournisseurRepository fournisseurRepository) {
            super();
            this.fournisseurRepository = fournisseurRepository;
        }

        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        Converter converter;

        @Override
        public Fournisseur saveFournisseur(FournisseurDto fournisseurDto)
        {

            Fournisseur fournisseur = converter.fournisseurdtoToEntity(fournisseurDto);

            return fournisseurRepository.save(fournisseur);
        }

/*
    @Override
    public List<NatureContrat> findFonctionnaireByStructure(String idstructure)
    {
        return fonctionnaireRepository.findByFonctionnaireAsCustom(idstructure);
    }
*/

        @Override
        public Fournisseur findFournisseurById(String id) {
            Optional<Fournisseur> result = fournisseurRepository.findById(id);
            if(result.isPresent()) {
                return result.get();
            }else {
                throw new InvalidConfigurationPropertyValueException("Fournisseur", "id", id);
            }
        }

    @Override
    public List<Fournisseur> findFournisseurByOrganisme(String organisme) {
        List<Fournisseur> result = fournisseurRepository.findByOrganisme(organisme);
            return result;
    }

        @Override
        public Boolean deleteFournisseur(String id) {
            fournisseurRepository.deleteById(id);
            return true;
        }

    @Override
    public List<Fournisseur> findAllFournisseurs()
    {
        return fournisseurRepository.findAll();
    }

        @Override
        public Fournisseur updateFournisseur(String id, Fournisseur fournisseurRequest) {
            Fournisseur fournisseur = fournisseurRepository.findById(id)
                    .orElseThrow(() -> new InvalidConfigurationPropertyValueException("Fournisseur", "id", id));
            fournisseur.setOrganisme(fournisseur.getOrganisme());
            fournisseur.setRaison_social(fournisseur.getRaison_social());
            fournisseur.setMatricule_fiscale(fournisseur.getMatricule_fiscale());
            fournisseur.setAdresse(fournisseur.getAdresse());
            fournisseur.setEmail(fournisseur.getEmail());
            fournisseur.setNum_tel(fournisseur.getNum_tel());
            fournisseur.setNum_fax(fournisseur.getNum_fax());
            fournisseur.setResponsable(fournisseur.getResponsable());
            return fournisseurRepository.save(fournisseur);
        }
}
