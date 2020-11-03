package ar.com.ada.api.empleados.sistema.comm;

public class EmailBody {
    public String content;
    public String email;
    public String subject;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public EmailBody(String content, String email, String subject) {
        this.content = content;
        this.email = email;
        this.subject = subject;
    }

    public String toString() {
        return "Entrega de un mail: " + this.getEmail();
    }
    
}
