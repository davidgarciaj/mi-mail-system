
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

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
    }
    
    /**
     * Método para obtener el siguiente email.
     */
    public MailItem getNextMailItem(){
        return server.getNextMailItem(user);
    
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
            System.out.println("No hay mas correos en tu bandeja.");
        }
    }
    
    /**
     * Enviar un mensaje a otro cliente.
     */
    public void sendMailItem(String paraQuien , String mensaje){
        MailItem cuenta = new MailItem(user , paraQuien , mensaje);
        server.post(cuenta);
    }
}
