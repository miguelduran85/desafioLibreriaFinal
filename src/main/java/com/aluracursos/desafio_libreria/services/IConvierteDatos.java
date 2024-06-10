package com.aluracursos.desafio_libreria.services;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}
