package com.annuaire.contratservice.tools;

import com.annuaire.contratservice.dto.*;
import com.annuaire.contratservice.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author karim hmadi

 * @description convert dto to entity and entity to dto

 */

@Component
public class Converter {
    public TypeContratDto typeentityToDto(TypeContrat typecontrat) {
        ModelMapper mapper =new ModelMapper();
        TypeContratDto map = mapper.map(typecontrat, TypeContratDto.class);
        return map;
    }
    public List<TypeContratDto> typeentityToDto(List<TypeContrat> typecontrat) {

        return	typecontrat.stream().map(x -> typeentityToDto(x)).collect(Collectors.toList());

    }


    public TypeContrat typedtoToEntity(TypeContratDto typeContratDto)
    {

        ModelMapper mapper = new ModelMapper();
        TypeContrat map = mapper.map(typeContratDto, TypeContrat.class);
        return map;
    }

    public List<TypeContrat> typedtoToEntity(List<TypeContratDto> lsttypedto)
    {

        return lsttypedto.stream().map(x -> typedtoToEntity(x)).collect(Collectors.toList());
    }

/** nature contrat **/

public NatureContratDto natureentityToDto(NatureContrat naturecontrat) {
    ModelMapper mapper =new ModelMapper();
    NatureContratDto map = mapper.map(naturecontrat, NatureContratDto.class);
    return map;
}
    public List<NatureContratDto> natureentityToDto(List<NatureContrat> naturecontrat) {

        return	naturecontrat.stream().map(x -> natureentityToDto(x)).collect(Collectors.toList());

    }


    public NatureContrat naturedtoToEntity(NatureContratDto natureContratDto)
    {

        ModelMapper mapper = new ModelMapper();
        NatureContrat map = mapper.map(natureContratDto, NatureContrat.class);
        return map;
    }

    public List<NatureContrat> naturedtoToEntity(List<NatureContratDto> lstnaturedto)
    {

        return lstnaturedto.stream().map(x -> naturedtoToEntity(x)).collect(Collectors.toList());
    }

    /** fournisseur **/

    public FournisseurDto fournisseurentityToDto(Fournisseur fournisseur) {
        ModelMapper mapper =new ModelMapper();
        FournisseurDto map = mapper.map(fournisseur, FournisseurDto.class);
        return map;
    }
    public List<FournisseurDto> fournisseurentityToDto(List<Fournisseur> fournisseur) {
        return	fournisseur.stream().map(x -> fournisseurentityToDto(x)).collect(Collectors.toList());
    }


    public Fournisseur fournisseurdtoToEntity(FournisseurDto fournisseurDto)
    {

        ModelMapper mapper = new ModelMapper();
        Fournisseur map = mapper.map(fournisseurDto, Fournisseur.class);
        return map;
    }

    public List<Fournisseur> fournisseurdtoToEntity(List<FournisseurDto> lstfournisseurdto)
    {
        return lstfournisseurdto.stream().map(x -> fournisseurdtoToEntity(x)).collect(Collectors.toList());
    }

    /** contrat **/

    public ContratDto contratentityToDto(Contrat contrat) {
        ModelMapper mapper =new ModelMapper();
        ContratDto map = mapper.map(contrat, ContratDto.class);
        return map;
    }
    public List<ContratDto> contratentityToDto(List<Contrat> contrat) {

        return	contrat.stream().map(x -> contratentityToDto(x)).collect(Collectors.toList());

    }


    public Contrat contratdtoToEntity(ContratDto contratDto)
    {

        ModelMapper mapper = new ModelMapper();
        Contrat map = mapper.map(contratDto, Contrat.class);
        return map;
    }

    public List<Contrat> contratdtoToEntity(List<ContratDto> lstcontratdto)
    {

        return lstcontratdto.stream().map(x -> contratdtoToEntity(x)).collect(Collectors.toList());
    }

    /** custom **/

    public CustomContratDto customentityToDto(CustomContrat customcontrat) {
        ModelMapper mapper =new ModelMapper();
        CustomContratDto map = mapper.map(customcontrat, CustomContratDto.class);
        return map;
    }
    public List<CustomContratDto> customentityToDto(List<CustomContrat> customcontrat) {

        return	customcontrat.stream().map(x -> customentityToDto(x)).collect(Collectors.toList());

    }


    public CustomContrat customdtoToEntity(CustomContratDto customcontratDto)
    {

        ModelMapper mapper = new ModelMapper();
        CustomContrat map = mapper.map(customcontratDto, CustomContrat.class);
        return map;
    }

    public List<CustomContrat> customdtoToEntity(List<CustomContratDto> lstcustomcontratdto)
    {

        return lstcustomcontratdto.stream().map(x -> customdtoToEntity(x)).collect(Collectors.toList());
    }
}
