package com.aluracursos.desafio_libreria.repository;

import com.aluracursos.desafio_libreria.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNombreAutor(String nombreAutor);

    @Query("SELECT a FROM Autor a WHERE a.añoDeNacimiento <= :fecha AND a.añoDeMuerte >= :fecha")
    List<Autor> findAutoresVivos(@Param("fecha")Integer fecha);

}
