package ar.com.ada.api.empleados.entities.calculo;

import java.math.BigDecimal;

import ar.com.ada.api.empleados.entities.*;

/**
 * ueldoCalculator Interface Strategy para calculo de sueldo
 */
public interface SueldoCalculator {

    BigDecimal calcularSueldo(Empleado empleado);

}