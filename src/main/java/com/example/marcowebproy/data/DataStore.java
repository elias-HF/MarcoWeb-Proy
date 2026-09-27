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
        Recurso r1 = new Recurso(1, "Laptop", null, "Disponible", "Buena");
        Recurso r2 = new Recurso(2, "Proyector Epson", null, "Disponible", "Buena");
        recursos.add(r1);
        recursos.add(r2);

        categorias.add(new Categoria(1, "Tecnología", "Equipos tecnológicos para uso académico.", r1, true));
        categorias.add(new Categoria(2, "Audiovisual", "Equipos utilizados para presentaciones.", r2, false));
        categorias.add(new Categoria(3, "Material académico", "Libros y materiales de consulta.", null, true));
        categorias.add(new Categoria(4, "Accesorios", "Accesorios y complementos tecnológicos.", null, false));
    }
}