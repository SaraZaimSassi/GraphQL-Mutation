package org.example.graphqlmutation.controller;

import org.example.graphqlmutation.dto.EtudiantDTO;
import org.example.graphqlmutation.model.Centre;
import org.example.graphqlmutation.model.Etudiant;
import org.example.graphqlmutation.repository.CentreRepository;
import org.example.graphqlmutation.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class EtudiantController {
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;
    @QueryMapping
    public List<Centre> getAllCentres(){
        return centreRepository.findAll();
    }
    @QueryMapping
    public List<Etudiant>listEtudiants(){
        return etudiantRepository.findAll();
    }
    @QueryMapping
    public Centre getCentre(@Argument int id){
        return centreRepository.findById((long) id).orElseThrow(
                ()->new RuntimeException(String.format("le centre %s n'existe pas !a",id))
        );
    }
    @QueryMapping
    public Etudiant getEtudiant(@Argument int id){
        return etudiantRepository.findById((long) id).orElseThrow(
                ()->new RuntimeException(String.format("l'étudiant %s n'existe pas !",id))
        );
    }
    @MutationMapping
    public String deleteEtudiant(@Argument int id){
        if(etudiantRepository.findById((long) id).isPresent()){
            etudiantRepository.deleteById((long) id);
            return String.format("l'étudiant %s est bien supprimé !",id);
        }
        return String.format("l'étudiant %s n'existe pas !",id);
    }
    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant){
        Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
        if(centre!=null){
            Etudiant et=new Etudiant();
            et.setNom(etudiant.nom());
            et.setPrenom(etudiant.prenom());
            et.setGenre(etudiant.genre());
            et.setCentre(centre);
            return etudiantRepository.save(et);
        }
        return null;
    }
    @MutationMapping
    public Etudiant updateEtudiant(@Argument int id,@Argument EtudiantDTO etudiant){
        Centre centre=centreRepository.findById(etudiant.centreId()).orElse(null);
        if(centre!=null) {
            if (etudiantRepository.findById((long) id).isPresent()) {
                Etudiant et = etudiantRepository.findById((long) id).get();
                et.setNom(etudiant.nom());
                et.setPrenom(etudiant.prenom());
                et.setGenre(etudiant.genre());
                et.setCentre(centre);
                return etudiantRepository.save(et);
            }
        }
        return null;
    }
//ajouter trois méthodes pour supprimer, modifier et ajouter un centre puis mettre à jour le schéma graphqls
}