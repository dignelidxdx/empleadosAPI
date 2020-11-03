package ar.com.ada.api.empleados.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.ada.api.empleados.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    // al crear este metodo no necesitamos crear un opcional solo es menos codigo
    Empleado findById(int id);

    @Query("Select c From Empleado c where c.categoria.categoriaId=:categoriaId")
    List<Empleado> buscarEmpleadosByCategory(Integer categoriaId);

    Empleado findByNombre(String nombre);

    Empleado findByDni(String id);
}