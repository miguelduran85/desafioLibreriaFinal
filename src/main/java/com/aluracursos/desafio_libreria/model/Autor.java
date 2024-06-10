package com.aluracursos.desafio_libreria.model;

import jakarta.persistence.*;

import java.util.HashSet;

import java.util.Set;

@Entity
@Table(name = "Autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreAutor;
    private Integer añoDeNacimiento;
    private Integer añoDeMuerte;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Set<Libro> libro = new HashSet<>();

    public Autor(){}

    public Autor( DatosAutor datosAutor){
        this.nombreAutor = datosAutor.nombreAutor();
        this.añoDeNacimiento = datosAutor.añoDeNacimiento();
        this.añoDeMuerte = datosAutor.añoDeMuerte();
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    public Integer getAñoDeNacimiento() {
        return añoDeNacimiento;
    }

    public void setAñoDeNacimiento(Integer añoDeNacimiento) {
        this.añoDeNacimiento = añoDeNacimiento;
    }

    public Integer getAñoDeMuerte() {
        return añoDeMuerte;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setAñoDeMuerte(Integer añoDeMuerte) {
        this.añoDeMuerte = añoDeMuerte;
    }
    public Set<Libro> getLibro() {
        return libro;
    }

    public void setLibro(Set<Libro> libros) {
        this.libro = libros;
        for (Libro libro : libros) {
            libro.setAutor(this);
        }
    }


    @Override
    public String toString() {
        return "* Nombre del Autor = " + nombreAutor + '\n' +
                "* Año de Nacimiento = " + añoDeNacimiento + '\n' +
                "* año de Muerte = " + añoDeMuerte + '\n';
    }
}
