package ar.com.ada.api.empleados.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="empleado")
public class Empleado {
    @Column(name="empleado_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empleadoId;
    private String nombre;
    private Integer edad; 
    private BigDecimal sueldo;
    @Column(name = "fecha_alta")
    private Date fechaAlta;
    @Column(name = "fecha_baja")
    private Date fechaBaja; 
    @Column(name = "estado_id")
	private Integer estadoId;
	private String dni;
	private String email;
    
    @ManyToOne
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

    public void setSueldo(BigDecimal sueldoBase) {
        this.sueldo = sueldoBase;
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

    @JsonIgnore
    public BigDecimal getVentasActuales() {

        Random randomGenerator = new Random();

        // Genero un numero rando hasta 10000
        double venta = randomGenerator.nextDouble() * 10000 + 1;
        // redondeo en 2 decimales el random truncando
        venta = ((long) (venta * 100)) / 100d;

        return new BigDecimal(venta);
    }

    public EmpleadoEstadoEnum getEstadoId() {
        return EmpleadoEstadoEnum.parse(this.estadoId);
    }

    public void setEstadoId(EmpleadoEstadoEnum estadoId) {
        this.estadoId = estadoId.getValue();
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
        // por el ManyToOne
        // a la lista de empleados va a agregarle el obj
        // devuelve la lista de empleados de la categoria actual y me agrega a mí mismo
        // (o sea, a categoria)
        this.categoria.getEmpleados().add(this);
    }

    /***
     * En este caso es un ENUMERADO con numeracion customizada En JAVA, los
     * enumerados con numeros customizados deben tener un constructor y un
     * comparador para poder funcionar correctamente
     */
    public enum EmpleadoEstadoEnum {
        DESCONOCIDO(0), PENDIENTEALTA(1), ACTIVO(2), LICENCIA(3), DESVINCULADO(99);

        private final int value;

        // NOTE: Enum constructor tiene que estar en privado
        private EmpleadoEstadoEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static EmpleadoEstadoEnum parse(int id) {
            EmpleadoEstadoEnum status = null; // Default
            for (EmpleadoEstadoEnum item : EmpleadoEstadoEnum.values()) {
                if (item.getValue() == id) {
                    status = item;
                    break;
                }
            }
            return status;
        }
    }
    
}