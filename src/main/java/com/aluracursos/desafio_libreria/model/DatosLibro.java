package com.aluracursos.desafio_libreria.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("languages") List <String> idiomas,
        @JsonAlias("download_count") Double numeroDescargas,
        @JsonAlias("authors")List <DatosAutor> autor

) {

}
