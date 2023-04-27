package com.example.etudaintinscriptionfiliereservice.services;


import com.example.etudaintinscriptionfiliereservice.dtos.RequestEtudiantDTo;
import com.example.etudaintinscriptionfiliereservice.dtos.ResponseEtudiantDTO;
import com.example.etudaintinscriptionfiliereservice.entities.Etudiant;
import com.example.etudaintinscriptionfiliereservice.exceptions.EntityNotFoundException;
import com.example.etudaintinscriptionfiliereservice.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EtudiantService implements EtudiantServiceInterface {

        @Autowired
        private EtudiantRepository etudiantRepository;

        public List<Etudiant> getAllEtudiants() {
            return etudiantRepository.findAll();
        }

        public Etudiant getEtudiantById(long id) throws EntityNotFoundException {
            return etudiantRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));
        }

        public Etudiant addEtudiant(RequestEtudiantDTo requestEtudiantDTo) {
            Etudiant etudiant = new Etudiant();
            etudiant.setNom(requestEtudiantDTo.getNom());
            etudiant.setPrenom(requestEtudiantDTo.getPrenom());
            etudiant.setApogee(requestEtudiantDTo.getApogee());
            return etudiantRepository.save(etudiant);
        }

        public Etudiant updateEtudiant(RequestEtudiantDTo requestEtudiantDTo) throws EntityNotFoundException {
            Etudiant etudiant = etudiantRepository.findById(requestEtudiantDTo.getId()).orElseThrow(() -> new EntityNotFoundException("Etudiant non trouvé"));
            etudiant.setNom(requestEtudiantDTo.getNom());
            etudiant.setPrenom(requestEtudiantDTo.getPrenom());
            etudiant.setApogee(requestEtudiantDTo.getApogee());
            return etudiantRepository.save(etudiant);
        }

        public Etudiant getEtudiantByApogee(long apogee) {
            return etudiantRepository.findByApogee(apogee);
        }

        public void deleteEtudiant(long etudiantId) throws EntityNotFoundException {
            if (etudiantRepository.existsById(etudiantId)) {
                etudiantRepository.deleteById(etudiantId);
            } else {
                throw new EntityNotFoundException("Etudiant non trouvé");
            }
        }
    }












