package ar.com.ada.api.empleados.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name="empleado")
public class Empleado {
    @Column(name="empleado_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empleadoId;
    private String nombre;
    private int edad; 
    private BigDecimal sueldo;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    private Date fechaBaja; 
    @Column(name = "estado_id")
    private int estadoId;
    
    @ManyToOne()
    @JoinColumn(name = "categoria_id", referencedColumnName = "categoria_id")
	private Categoria categoria;



	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public BigDecimal getSueldo() {
		return sueldo;
	}

	public void setSueldo(BigDecimal sueldo) {
		this.sueldo = sueldo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public int getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(int estadoId) {
		this.estadoId = estadoId;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
    
    
}