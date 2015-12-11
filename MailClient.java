
/**
 * Write a description of class MailClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MailClient
{
    // Objeto que indica el servidor de mensajes
    private MailServer server;
    //La dirección email del usuario de este servidor
    private String user;
    //Último mensaje recivido
    private MailItem lastEmail;
    //Determina si el último MailItem que te han enviado es spam.
    private boolean spam;

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        lastEmail = null;
        spam = false;
    }
    
    /**
     * Método para obtener el siguiente email.
     */
    public MailItem getNextMailItem(){
        MailItem correo = null;
        if(server.howManyMailItems(user) > 0){
            lastEmail = server.getNextMailItem(user);
            String mensage = lastEmail.getMessage();
            if(mensage.contains("trabajo")){
                correo = lastEmail;                
            }
            else if(mensage.contains("regalo") || mensage.contains("promocion")){
                spam = true;
                correo = null;
            }
            else{
                correo = lastEmail;
            }
        }        
        return correo;
    
    }
    
    /**
     * Método que indica cuantos email tenemos en el servidor
     */
    public void cuantosEmailTenemosEnElServidor(){
        int emails = server.howManyMailItems(user);
        if(emails != 0){
            if(emails == 1){
                System.out.println("Actualmente tienes " + emails + " email.");
            }
        }
        else{
            System.out.println("No hay mas correos en tu bandeja.");
        }
    }
    
    /**
     * Método para ver el siguiente email.
     */
    public void printNextMailItem(){
        MailItem correo = getNextMailItem();
        if(correo != null){
             correo.print();
           
        }
        else{
            if(spam){
                 System.out.println("El último email recivido es un spam.");
            }
            else{
                System.out.println("No hay mas correos en tu bandeja.");
            }
        }
    }
    
    /**
     * Enviar un mensaje a otro cliente.
     */
    public void sendMailItem(String paraQuien ,String subject , String mensaje){
        MailItem cuenta = new MailItem(user , paraQuien , mensaje, subject);
        server.post(cuenta);
    }
    
    /**
     * Método para obtener el siguiente email y poder responde.
     */
    public MailItem getNextMailItemAndAutorespond(){
        MailItem enviado = getNextMailItem();
        if(enviado != null){
            sendMailItem(enviado.getFrom(), "RE:" + enviado.getSubject(),
                    "Estamos de vacaciones, lo sentimos mucho.\n" + 
                    "#############################################\n" + 
                    enviado.getMessage());
        }        
        return enviado ;
    }
    
    /**
     * Imprime el último mensaje recivido.
     */
    public void printLastEmail(){
        if(lastEmail != null){
             lastEmail.print();
        }
        else{
            System.out.println("No hay mensajes disponibles");
        }
    }
}
