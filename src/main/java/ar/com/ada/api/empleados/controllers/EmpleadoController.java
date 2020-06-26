package ar.com.ada.api.empleados.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.models.response.GenericResponse;
import ar.com.ada.api.empleados.services.EmpleadoService;

@RestController
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/empleados")
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
        empleadoService.crearEmpleado(empleado);
        GenericResponse gR = new GenericResponse();
        gR.id = empleado.getEmpleadoId();
        gR.isOk = true;
        gR.message = "Empleado creada con exito";

        return ResponseEntity.ok(gR);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleados(){
        return ResponseEntity.ok(empleadoService.obtenerEmpleado());
   
    }
    
}