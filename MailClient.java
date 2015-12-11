
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
    //El email recivido mas largo.
    private MailItem mostLargeMail;
    //El útimo spam.
    private MailItem lastSpam;
    //Contador de mensajes enviados.
    private int howManyMailSend;
    //Contador de mensajes recividos.
    private int howManyMailHave;
    //Contador de mensajes spam.
    private int howManyMailSpam;
    

    /**
     * Constructor for objects of class MailClient
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        lastEmail = null;
        spam = false;
        mostLargeMail = null;
        lastSpam = null;
        howManyMailSend = 0;
        howManyMailHave = 0;
        howManyMailSpam = 0;
    }
    
    /**
     * Método para obtener el siguiente email.
     */
    public MailItem getNextMailItem(){
        MailItem correo = null;
        if(server.howManyMailItems(user) > 0){
            lastEmail = server.getNextMailItem(user);
            String mensaje = lastEmail.getMessage();
            howManyMailHave++;
            if(mostLargeMail != null){
                String largeMail = mostLargeMail.getMessage();
                if(mensaje.length() > largeMail.length()){
                    mostLargeMail = lastEmail;
                }
            }
            else{
                 mostLargeMail = lastEmail;
            }
            if(mensaje.contains("trabajo")){
                correo = lastEmail;                
            }
            else if(mensaje.contains("regalo") || mensaje.contains("promocion")){
                spam = true;
                correo = null;
                howManyMailSpam++;
                lastSpam = lastEmail;
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
        howManyMailSend++;
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
                    howManyMailSend++;
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
    
    /**
     * Muestra el número de mensajes enviados recividos y spam,
     * el porcentaje de spam que recives y quien te envio el
     * mensaje mas largo y de cuantos carazteres lo envio.
     */
    public void showStats(){
         int porcentaje;
         if(mostLargeMail != null){
             porcentaje = (howManyMailSpam * 100)/howManyMailHave;
         }
         else{
             porcentaje = 0;
         }
         System.out.println("Mensajes enviados: " + howManyMailSend);
         System.out.println("Mensajes recividos: " + howManyMailHave);
         System.out.println("Mensajes que son spam: " + howManyMailSpam);
         System.out.println("Porcentaje de spam: " + porcentaje + "%");
         if(mostLargeMail != null){   
            String largeMail = mostLargeMail.getMessage();
            System.out.println("El Mensaje más largo a sido de " + mostLargeMail.getFrom() 
                            + " y fue de " + largeMail.length() + " carácteres.");
         }
         else{
             System.out.println("No has recibido ningún mensaje");
         }
    }
    /**
     * Muestra el último MailItem spam que has recivido
     */
    public void showInfoLastSpam(){
        if(lastSpam != null){
            lastSpam.print();
        }
        else{
            System.out.println("No has recivido ningún email que sea spam");
        }
    }
}
