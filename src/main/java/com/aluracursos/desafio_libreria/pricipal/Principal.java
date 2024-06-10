package com.aluracursos.desafio_libreria.pricipal;

import com.aluracursos.desafio_libreria.model.*;
import com.aluracursos.desafio_libreria.repository.AutorRepository;
import com.aluracursos.desafio_libreria.repository.LibrosRepository;
import com.aluracursos.desafio_libreria.services.ConsumoAPI;
import com.aluracursos.desafio_libreria.services.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversorDatos = new ConvierteDatos();
    private List<Autor> autorList = new ArrayList<>();
    private List<Libro> libros;
    private LibrosRepository libroRepositorio;
    private AutorRepository autorRepositorio;



    public Principal(LibrosRepository libroRepository, AutorRepository autorRepository) {

        this.libroRepositorio = libroRepository;
        this.autorRepositorio = autorRepository;
    }

    public void Elmenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ****************************
                    1 - Buscar libro por titulo
                    2 - Lista de libros registrados
                    3 - Lista de autores regitrados
                    4 - Lista de autores vivos en determinado año
                    5 - Lista de libros por idioma
                    
                    0 - Salir
                    ******************************""";
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    autoresVivos();
                    break;
                case 5:
                    busquedaPorIdioma();
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println("escribe el nombre del libro");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "%20"));
        var datos = conversorDatos.obtenerDatos(json, Datos.class);
        return datos;
    }

    private void buscarLibro() {

        Optional<DatosLibro> libroBuscado = getDatosLibro().resultados().stream()
                .findFirst();

        if (libroBuscado.isPresent()) {

            DatosLibro datosLibro = libroBuscado.get();
            Optional<Libro> busquedaLibro = libroRepositorio.findByTituloContainsIgnoreCase(datosLibro.titulo());

            DatosAutor datosAutor = datosLibro.autor().get(0);
            Optional<Autor> busquedaAutor = autorRepositorio.findByNombreAutor(datosAutor.nombreAutor());

            if (busquedaAutor.isPresent()) {
                System.out.println("Autor registrado");
            }

            if (busquedaLibro.isPresent()) {

                System.out.println("**ESTE LIBRO YA SE ENCUENTRA REGISTRASDO**");

            } else {
                Autor autor = new Autor(datosAutor);
                Libro libro = new Libro(datosLibro, autor);
                libroRepositorio.save(libro);

                String muestraLibro = """
                        ----****************************----
                        -**Este es el libro de tu busqueda**
                        ____________________________________
                        * TITULO .- %s
                        * AUTOR ._ %s
                        * IDIOMA .- %s
                        * NUMERO DE DESCARGAS .- %.0f
                                                            
                        -------------------------------------
                        ***EL LIBRO SE AGREGO A LA LIBRERIA**
                        *************************************
                        """.formatted(libro.getTitulo(), autor.getNombreAutor(), libro.getIdioma(), libro.getNumeroDescargas());
                System.out.println(muestraLibro);
            }

        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void mostrarLibros() {
        libros = libroRepositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void mostrarAutores() {
        autorList = autorRepositorio.findAll();
        autorList.stream()
                .forEach(System.out::println);
    }

    private void autoresVivos() {

        System.out.println("Ingrese el año para buscar los autores vivos");

        while (!teclado.hasNextInt()) {
            System.out.println("Formato inválido para el año");
            teclado.nextLine();
        }
        int fecha = teclado.nextInt();

        List<Autor>autvivos = autorRepositorio.findAutoresVivos(fecha);

            if(autvivos.isEmpty()){
                System.out.println("No tenemos autores registrados en ese periodo");
            }else {
                System.out.println("Autores que estaban vivos en el año "+ fecha);

                autvivos.forEach(System.out::println);
            }

    }

    private void busquedaPorIdioma() {
        var opc = -1;
        while (opc != 0) {
            System.out.println("""
                    **********************
                    * 1) Español (ES)
                    * 2) Inglés (EN)
                    * 3) Francés (FR)
                    * 4) Italiano (IT)
                    * 5) Portugués (PT)
                                            
                    * 0) menú principal
                                    
                    Ingrese el número de opción 
                    """);

            opc = teclado.nextInt();
            teclado.nextLine();
            switch (opc){
                case 1:
                    libros = libroRepositorio.findByIdiomaContaining("es");
                    if(!libros.isEmpty()){
                        libros.stream()
                                .forEach(System.out::println);
                    }else {
                        System.out.println("No tenemos libros registrados");
                    }
                    break;
                case 2:
                    libros = libroRepositorio.findByIdiomaContaining("en");
                    if(!libros.isEmpty()){
                        libros.stream()
                                .forEach(System.out::println);
                    }else {
                        System.out.println("No tenemos libros registrados");
                    }
                    break;
                case 3:
                    libros = libroRepositorio.findByIdiomaContaining("fr");
                    if(!libros.isEmpty()){
                        libros.stream()
                                .forEach(System.out::println);
                    }else {
                        System.out.println("No tenemos libros registrados");
                    }
                    break;
                case 4:
                    libros = libroRepositorio.findByIdiomaContaining("it");
                    if(!libros.isEmpty()){
                        libros.stream()
                                .forEach(System.out::println);
                    }else {
                        System.out.println("No tenemos libros registrados");
                    }
                    break;
                case 5:
                    libros = libroRepositorio.findByIdiomaContaining("pt");
                    if(!libros.isEmpty()){
                        libros.stream()
                                .forEach(System.out::println);
                    }else {
                        System.out.println("No tenemos libros registrados");
                    }
                    break;
                default:
                    System.out.println("Opción inválida");



            }
        }
    }
}
