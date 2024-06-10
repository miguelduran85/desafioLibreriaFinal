package com.aluracursos.desafio_libreria.model;

import jakarta.persistence.*;



@Entity
@Table(name = "Libros")
public class Libro{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long Id;
   @Column(unique = true)
    private String titulo;
    private  String idioma;
    private Double numeroDescargas;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)

    private  Autor autor;


    public Libro(){}
    public Libro(DatosLibro datosLibro, Autor autor){
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idiomas().toString();
        this.numeroDescargas = datosLibro.numeroDescargas();
        this.autor = autor;
    }



    @Override
    public String toString() {
        return "-----------Libro-----------------"+ '\n'+
                "* Titulo =" + titulo + '\n' +
                "* Autor =" + autor + '\n' +
                "* Idioma='" + idioma + '\n' +
                "* numero de descargas=" + numeroDescargas + '\n' +
                 "-----------------------------------------";
    }

    // getters&setters
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        idioma = idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
        if (autor != null && !autor.getLibro().contains(this)) {
            autor.getLibro().add(this);
        }
    }
}

