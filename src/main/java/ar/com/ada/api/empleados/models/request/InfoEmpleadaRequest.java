package ar.com.ada.api.empleados.models.request;

import java.math.BigDecimal;

import ar.com.ada.api.empleados.entities.Categoria;

public class InfoEmpleadaRequest {
    
    public String nombre;
    public int edad;
    public BigDecimal sueldo;
    public int categoriaId;
}