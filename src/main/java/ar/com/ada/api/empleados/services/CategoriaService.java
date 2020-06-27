package ar.com.ada.api.empleados.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.repos.CategoriaRepository;

@Service
public class CategoriaService {
    
    @Autowired
    private CategoriaRepository repo;

    public void crearCategoria(Categoria categoria) {
        repo.save(categoria);
    }
    public List<Categoria> obtenerCategoria() {
        return (repo.findAll());
    }
    public Categoria obtenerPorId(int categoriaId) {
        Optional<Categoria> c = repo.findById(categoriaId);
        if (c.isPresent())
            return c.get();
        return null;
    }
    
    

    
}