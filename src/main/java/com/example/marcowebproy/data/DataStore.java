package com.example.marcowebproy.data;

import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.model.Recurso;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataStore {
    public List<Categoria> categorias = new ArrayList<>();
    public List<Recurso> recursos = new ArrayList<>();

    public DataStore() {
        Categoria tecnologia = new Categoria(1, "Tecnología", "Equipos tecnológicos para uso académico.", null, true);
        Categoria audiovisual = new Categoria(2, "Audiovisual", "Equipos utilizados para presentaciones.", null, true);
        Categoria materialAcademico = new Categoria(3, "Material académico", "Libros y materiales de consulta.", null, true);
        Categoria accesorios = new Categoria(4, "Accesorios", "Accesorios y complementos tecnológicos.", null, true);

        categorias.add(tecnologia);
        categorias.add(audiovisual);
        categorias.add(materialAcademico);
        categorias.add(accesorios);

        Recurso r1 = new Recurso(1, "bi-laptop", "Laptop Dell Core i7", tecnologia, "Biblioteca - Piso 3", "Disponible", "Buena", true);
        Recurso r2 = new Recurso(2, "bi-projector","Proyector Epson", audiovisual, "Laboratorio de Sistemas", "Disponible", "Buena", true);
        Recurso r3 = new Recurso(3, "bi-book","Libro de Ingeniería de Software", materialAcademico, "Biblioteca - Piso 1", "Disponible", "Buena", false);

        recursos.add(r1);
        recursos.add(r2);
        recursos.add(r3);
    }
}