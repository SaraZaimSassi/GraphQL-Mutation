package org.example.graphqlmutation.dto;

import org.example.graphqlmutation.enums.Genre;

public record EtudiantDTO (
        String nom,
        String prenom,
        Genre genre,
        Long centreId
){ }