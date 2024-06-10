package com.aluracursos.desafio_libreria.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor (
        @JsonAlias("name") String nombreAutor,
        @JsonAlias("birth_year") Integer añoDeNacimiento,
        @JsonAlias("death_year") Integer añoDeMuerte
){
}
