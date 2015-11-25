
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

    /**
     * Constructor for objects of class MailItem
     */
    public MailItem(String who, String send, String envia)
    {
        from = who;
        to = send;
        message = envia;
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
     * Imprime en pantalla de quien, para quien y cual es el mensaje.
     */
    public void print(){
        System.out.println("From: " + from);
        System.out.println(message);
        System.out.println("To: " + to + " with love.");
    }
}
