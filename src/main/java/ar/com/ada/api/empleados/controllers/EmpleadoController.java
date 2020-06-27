package ar.com.ada.api.empleados.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleados.entities.Categoria;
import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.models.request.InfoEmpleadaRequest;
import ar.com.ada.api.empleados.models.request.NombreModifRequest;
import ar.com.ada.api.empleados.models.request.SueldoModifRequest;
import ar.com.ada.api.empleados.models.response.GenericResponse;
import ar.com.ada.api.empleados.services.CategoriaService;
import ar.com.ada.api.empleados.services.EmpleadoService;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    CategoriaService categoriaService;


    @PostMapping("/empleadas")
    public ResponseEntity<?> crearEmpleado(@RequestBody InfoEmpleadaRequest info) {
        Empleado empleada = new Empleado();
        empleada.setNombre(info.nombre);
        empleada.setEdad(info.edad);
        empleada.setSueldo(info.sueldo);
        empleada.setFechaAlta(new Date());
        Categoria categoria = categoriaService.obtenerPorId(info.categoriaId);
        empleada.setCategoria(categoria);
        empleada.setEstadoId(0);

        empleadoService.crearEmpleado(empleada);
        GenericResponse gR = new GenericResponse();
        gR.id = empleada.getEmpleadoId();
        gR.isOk = true;
        gR.message = "Empleado creada con exito";

        return ResponseEntity.ok(gR);
    }

    @GetMapping("/empleadas")
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        return ResponseEntity.ok(empleadoService.obtenerEmpleados());
   
    }
    @GetMapping("/empleadas/{id}")
    public ResponseEntity<Empleado> obtenerEmpleada(@PathVariable int id) {
        Empleado empleada = empleadoService.obtenerPorId(id);
        if(empleada == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleada);
    }

    @GetMapping("/empleadas/categorias/{categoriaId}")
    public ResponseEntity<List<Empleado>> listarPorCategoriaId(@PathVariable int categoriaId) {
        List<Empleado> listaEmpleadas = categoriaService.obtenerPorId(categoriaId).getEmpleados();
        return ResponseEntity.ok(listaEmpleadas);
    }

    @PutMapping("/empleadas/{id}/sueldos")
    public ResponseEntity<GenericResponse> actualizarSuelto(@PathVariable int id, @RequestBody SueldoModifRequest sueldoRequest) {
        Empleado empleada = empleadoService.obtenerPorId(id);
        if(empleada  == null) {
            return ResponseEntity.notFound().build();            
        }
        empleada.setSueldo(sueldoRequest.sueldoNuevo);
        empleadoService.grabar(empleada);
        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.id = empleada.getEmpleadoId();
        gR.message = "Sueldo actualizado con exito al empleado: " + empleada.getNombre();
        return ResponseEntity.ok(gR);
    }
    @PutMapping("/empleadas/{id}/nombre")
    public ResponseEntity<GenericResponse> actualizarNombre(@PathVariable int id, @RequestBody NombreModifRequest nombreRequest) {
        Empleado empleada = empleadoService.obtenerPorId(id);
        if(empleada  == null) {
            return ResponseEntity.notFound().build();            
        }
        empleada.setNombre(nombreRequest.nombreActualizado);
        empleadoService.grabar(empleada);
        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.id = empleada.getEmpleadoId();
        gR.message = "Nombre actualizado con exito al empleado id: " + empleada.getEmpleadoId();
        return ResponseEntity.ok(gR);
    }
    @DeleteMapping("/empleadas/{id}")
    public ResponseEntity<GenericResponse> bajaEmpleada(@PathVariable int id) {
        Empleado empleada = empleadoService.obtenerPorId(id);
        if(empleada == null){
            return ResponseEntity.notFound().build();
        }
        empleada.setFechaBaja(new Date());
        empleada.setEstadoId(1);
        empleadoService.grabar(empleada);
        GenericResponse gR = new GenericResponse();
        gR.isOk = true;
        gR.id = empleada.getEmpleadoId();
        gR.message = "Empleada dada de baja";
        return ResponseEntity.ok(gR);
    }

    
}