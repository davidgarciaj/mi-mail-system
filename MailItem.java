
/**
 * Contenedor del mensaje que se envia entre dos clientes.
 * 
 * @author (David Garcia) 
 * @version (1.0)
 */
public class MailItem
{
    // Indica de quien es el mensaje
    private String from;
    // Indica para quien es el mensaje
    private String to;
    // Contiene el texto del mensaje
    private String message;
    //Indica el motivo por el que se ha escrito el mensaje
    private String subject;

    /**
     * Constructor for objects of class MailItem
     */
    public MailItem(String from, String to, String message, String subject)
    {
        this.from = from;
        this.to = to;
        this.message = message;
        this.subject = subject;
    }

    /**
     *  Dice quien envia el mensaje.
     */
    public String getFrom(){
        return from;
    }

    /**
     *  Dice para quien es el mensaje.
     */
    public String getTo(){
        return to;
    }

    /**
     *  Dice que contiene el mensaje.
     */
    public String getMessage(){
        return message;
    }
    
    /**
     *  Dice el asunto del mensaje.
     */
    public String getSubject(){
        return subject;
    }
    
    /**
     * Imprime en pantalla de quien, para quien y cual es el mensaje.
     */
    public void print(){
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println(message);
        System.out.println("From: " + from + " with love.");
    }
}
