package ar.com.ada.api.empleados.exception;

import ar.com.ada.api.empleados.entities.Empleado;

public class EmpleadoUnderAgeException extends RuntimeException {

    public static EmpleadoUnderAgeException of(Empleado empleado) {
        return new EmpleadoUnderAgeException(empleado);
    }

    public EmpleadoUnderAgeException(String customMessage) {
        super(customMessage);
    }

    /**
     * Notice I have made this method 'private', this forces developer to use static constructor 'of' above
     * so code will be more 'idiomatic' when instantiating this object.
     *
     * The difference looks like the following:
     * StudentUnderAgeException exception = StudentUnderAgeException.of(someStudent);
     * vs
     * StudentUnderAgeException exception = new StudentUnderAgeException(someStudent);
     *
     * Note: in this particular case, this idiom falls heavily on 'debate', depending of coding styles.
     * @param student
     */
    private EmpleadoUnderAgeException(Empleado student) {
        super(String.format("Student %s with age %d is considered under adult age.",
                student.getNombre(),
                student.getDni()));
    }


}
