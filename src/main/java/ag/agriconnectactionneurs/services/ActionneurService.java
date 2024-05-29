package ag.agriconnectactionneurs.services;

import ag.agriconnectactionneurs.entities.Actionneur;
import ag.agriconnectactionneurs.entities.EtatActionneur;
import ag.agriconnectactionneurs.exceptions.ActionneurNotFoundException;
import ag.agriconnectactionneurs.exceptions.EtatException;
import ag.agriconnectactionneurs.repositories.ActionneurRepository;
import ag.agriconnectactionneurs.websocket.ActionneurWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class ActionneurService {

    private ActionneurRepository actionneurRepository;
    private final ActionneurWebSocketHandler webSocketHandler;
    private final ConcurrentHashMap<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    public ActionneurService(ActionneurRepository actionneurRepository, ActionneurWebSocketHandler webSocketHandler) {
        this.actionneurRepository = actionneurRepository;
        this.webSocketHandler = webSocketHandler;
    }


    public Actionneur save(Actionneur actionneur){
        return actionneurRepository.save(actionneur);
    }

    public Actionneur update(Long id, Actionneur actionneur) throws ActionneurNotFoundException {
        Actionneur updatedActionneur = actionneurRepository.findById(id).orElseThrow(() -> new ActionneurNotFoundException());
        updatedActionneur.setId(actionneur.getId());
        updatedActionneur.setLatitude(actionneur.getLatitude());
        updatedActionneur.setLongitude(actionneur.getLongitude());
        updatedActionneur.setEtat(actionneur.getEtat());
        return actionneurRepository.save(updatedActionneur);
    }

    public Actionneur getActionneur(Long id) throws ActionneurNotFoundException {
        return actionneurRepository.findById(id).orElseThrow(()-> new ActionneurNotFoundException());
    }

    public List<Actionneur> getAllActionneurs(){
        return actionneurRepository.findAll();
    }

    public void deleteActionneur(Long id) throws ActionneurNotFoundException {
        Actionneur deletedActionneur = actionneurRepository.findById(id).orElseThrow(() -> new ActionneurNotFoundException());
        actionneurRepository.delete(deletedActionneur);
    }

    public void triggerActionneur(Long id, long duration) throws Exception {
        Actionneur actionneur = actionneurRepository.findById(id).orElseThrow(ActionneurNotFoundException::new);

        if(actionneur.getEtat() == EtatActionneur.ACTIVE) {
            throw new EtatException("L'actionneur est déjà activé");
        }

        actionneur.setEtat(EtatActionneur.ACTIVE);
        actionneurRepository.save(actionneur);
        webSocketHandler.notifyClient( actionneur.getId() , actionneur.getEtat().toString());
        System.out.println("Actionneur #" + id + " activé pour " + duration + " millisecondes.");
        delayDesactivation(id, duration);
    }

    @Async
    protected void delayDesactivation(Long id, long duration) {
        try {
            Thread.sleep(duration);
            Actionneur actionneur = actionneurRepository.findById(id).orElseThrow(ActionneurNotFoundException::new);
            actionneur.setEtat(EtatActionneur.DESACTIVE);
            actionneurRepository.save(actionneur);

            System.out.println("Actionneur #" + id + " désactivé après " + duration + " millisecondes.");
            webSocketHandler.notifyClient( actionneur.getId() , actionneur.getEtat().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
