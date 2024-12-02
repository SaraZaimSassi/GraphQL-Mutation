package org.example.graphqlmutation.repository;

import org.example.graphqlmutation.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
}
