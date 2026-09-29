package com.example.marcowebproy.controller;

import com.example.marcowebproy.model.Categoria;
import com.example.marcowebproy.data.DataStore;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class categoriaControlller {
    private final DataStore dataStore;

    public categoriaControlller(DataStore dataStore) {this.dataStore = dataStore;}

    @GetMapping("/empleado/categorias")
    public String categorias(Model model) {
        model.addAttribute("categorias", dataStore.categorias);

        return "compoEmpleado/categorias";
    }

    @PostMapping("/empleado/categorias/guardar")
    public String guardarCategoria(@RequestParam String nombre, @RequestParam String descripcion){
        int nuevoID = dataStore.categorias.size() + 1;
        Categoria categoria = new Categoria(nuevoID, nombre, descripcion, null, true);

        dataStore.categorias.add(categoria);

        return "redirect:/empleado/categorias";
    }

    @PostMapping("/empleado/categorias/modificar")
    public String modificarCategoria(@RequestParam int id, @RequestParam String nombre, @RequestParam String descripcion) {
        Categoria categoria = dataStore.categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);

        if (categoria != null) {
            categoria.setNombre(nombre);
            categoria.setDescripcion(descripcion);
        }

        return "redirect:/empleado/categorias";
    }

    @PostMapping("/empleado/categorias/desactivar")
    public String desactivarCategoria(@RequestParam int id){
        Categoria categoria = dataStore.categorias.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
        if (categoria != null) {categoria.setEstado(false);}

        return "redirect:/empleado/categorias";
    }
}