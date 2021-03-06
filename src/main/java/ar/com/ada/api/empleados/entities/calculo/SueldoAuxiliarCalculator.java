package ar.com.ada.api.empleados.entities.calculo;

import java.math.BigDecimal;

import ar.com.ada.api.empleados.entities.*;

/**
 * Strategy SueldoAuxiliarCalculator En este caso el sueldo siempre es el base
 * de la categoria.
 */
public class SueldoAuxiliarCalculator implements SueldoCalculator {

    @Override
    public BigDecimal calcularSueldo(Empleado empleado) {
        return empleado.getCategoria().getSueldoBase();
    }

}