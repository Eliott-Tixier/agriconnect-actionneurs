package ag.agriconnectactionneurs.exceptions;

public class ActionneurNotFoundException extends Exception{
    public ActionneurNotFoundException(){
        super("Actionneur non trouvé");
    }
}
