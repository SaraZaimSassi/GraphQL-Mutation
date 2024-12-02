package org.example.graphqlmutation.repository;

import org.example.graphqlmutation.model.Centre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentreRepository extends JpaRepository<Centre,Long> {
}
