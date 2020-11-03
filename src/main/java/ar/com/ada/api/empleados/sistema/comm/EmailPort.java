package ar.com.ada.api.empleados.sistema.comm;

public interface EmailPort {
    public boolean sendEmail(EmailBody emailBody);
}
