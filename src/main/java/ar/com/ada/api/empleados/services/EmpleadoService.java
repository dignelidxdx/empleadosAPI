package ar.com.ada.api.empleados.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.empleados.entities.Empleado;
import ar.com.ada.api.empleados.entities.Empleado.EmpleadoEstadoEnum;
import ar.com.ada.api.empleados.repos.EmpleadoRepository;
import ar.com.ada.api.empleados.sistema.comm.EmailBody;
import ar.com.ada.api.empleados.sistema.comm.EmailService;

@Service
public class EmpleadoService {
    
    private EmpleadoRepository repo;
    @Autowired
    public EmpleadoService(EmpleadoRepository repo) {
        this.repo = repo;
    }
    @Autowired
    private EmailService emailService;

    public void crearEmpleado(Empleado empleado) {
        repo.save(empleado);
        EmailBody emailBody = new EmailBody("Hola", empleado.getEmail(), "subject");
      //  emailService.sendEmail(emailBody);
    }
    public List<Empleado> obtenerEmpleados() {
        return (repo.findAll());
    }
	public Empleado obtenerPorId(Integer id) throws Exception {
        Optional<Empleado> getEmpl = repo.findById(id);
        if(!getEmpl.isPresent())
            throw new Exception("El empleado no existe");
		return getEmpl.get();
    }
    
    public List<Empleado> buscarEmpleadosByCategory(Integer idCategory) {

        return repo.buscarEmpleadosByCategory(idCategory);
    }

     /***
     * Cambio el return del metodo grabar para que devuelva un enum de validacion Si
     * todo ok, graba.
     * 
     * @param empleada
     * @return
     */
    public EmpleadoValidationType grabar(Empleado empleada) {

        EmpleadoValidationType resultado = verificarEmpleado(empleada);
        if (resultado != EmpleadoValidationType.EMPLEADO_OK)
            return resultado;

        repo.save(empleada);

        return EmpleadoValidationType.EMPLEADO_OK;

    }

    public enum EmpleadoValidationType {

        EMPLEADO_OK, EMPLEADO_DUPLICADO, EMPLEADO_INVALIDO, SUELDO_NULO, EDAD_INVALIDA, NOMBRE_INVALIDO, DNI_INVALIDO,
        EMPLEADO_DATOS_INVALIDOS

    }

    /**
     * Verifica que el nombre no est� nulo, La edad sea mayor a 0, El sueldo sea
     * mayor a 0, El estado, la fecha de alta y baja no est�n en nulo.
     * 
     * @param empleado
     * @return
     */

    public EmpleadoValidationType verificarEmpleado(Empleado empleado) {

        if (empleado.getNombre() == null)
            return EmpleadoValidationType.NOMBRE_INVALIDO;

        if (empleado.getEdad() <= 0)
            return EmpleadoValidationType.EDAD_INVALIDA;

        if (empleado.getSueldo().compareTo(new BigDecimal(0)) <= 0)
            return EmpleadoValidationType.SUELDO_NULO;
        if (empleado.getEstadoId() == EmpleadoEstadoEnum.DESCONOCIDO)
            return EmpleadoValidationType.EMPLEADO_DATOS_INVALIDOS; // ACA GENERICO
        if (empleado.getFechaAlta() == null)
            return EmpleadoValidationType.EMPLEADO_DATOS_INVALIDOS;
        if (empleado.getDni() != "0")
            return EmpleadoValidationType.DNI_INVALIDO;

        Empleado e = repo.findByDni(empleado.getDni());
        if (e != null) {
            if (empleado.getEmpleadoId() != 0) {
                if ((empleado.getEmpleadoId() != e.getEmpleadoId())) {
                    return EmpleadoValidationType.EMPLEADO_DUPLICADO;
                } else {
                    return EmpleadoValidationType.EMPLEADO_OK;
                }
            } else
                return EmpleadoValidationType.EMPLEADO_DUPLICADO;

        }
        return EmpleadoValidationType.EMPLEADO_OK;
    }
    
}