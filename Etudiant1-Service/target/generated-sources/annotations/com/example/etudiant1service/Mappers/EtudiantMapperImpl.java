package com.example.etudiant1service.Mappers;

import com.example.etudiant1service.DTO.RequestEtudiantDTo;
import com.example.etudiant1service.DTO.ResponseEtudiantDTO;
import com.example.etudiant1service.Entities.Etudiant;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-30T21:47:43+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class EtudiantMapperImpl implements EtudiantMapper {

    @Override
    public Etudiant dtoToModel(RequestEtudiantDTo requestEtudiantDTo) {
        if ( requestEtudiantDTo == null ) {
            return null;
        }

        Etudiant etudiant = new Etudiant();

        etudiant.setId( requestEtudiantDTo.getId() );
        etudiant.setApogee( requestEtudiantDTo.getApogee() );
        etudiant.setFirstname( requestEtudiantDTo.getFirstname() );
        etudiant.setLastname( requestEtudiantDTo.getLastname() );
        etudiant.setTel( requestEtudiantDTo.getTel() );
        etudiant.setVille( requestEtudiantDTo.getVille() );
        etudiant.setAdress( requestEtudiantDTo.getAdress() );

        return etudiant;
    }

    @Override
    public ResponseEtudiantDTO modelToDto(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        ResponseEtudiantDTO responseEtudiantDTO = new ResponseEtudiantDTO();

        responseEtudiantDTO.setId( etudiant.getId() );
        responseEtudiantDTO.setApogee( etudiant.getApogee() );
        responseEtudiantDTO.setFirstname( etudiant.getFirstname() );
        responseEtudiantDTO.setLastname( etudiant.getLastname() );
        responseEtudiantDTO.setTel( etudiant.getTel() );
        responseEtudiantDTO.setVille( etudiant.getVille() );
        responseEtudiantDTO.setAdress( etudiant.getAdress() );

        return responseEtudiantDTO;
    }

    @Override
    public List<ResponseEtudiantDTO> modelToDtos(List<Etudiant> etudiants) {
        if ( etudiants == null ) {
            return null;
        }

        List<ResponseEtudiantDTO> list = new ArrayList<ResponseEtudiantDTO>( etudiants.size() );
        for ( Etudiant etudiant : etudiants ) {
            list.add( modelToDto( etudiant ) );
        }

        return list;
    }
}
