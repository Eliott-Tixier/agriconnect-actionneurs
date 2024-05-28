package ag.agriconnectactionneurs.services;

import ag.agriconnectactionneurs.entities.Actionneur;
import ag.agriconnectactionneurs.entities.EtatActionneur;
import ag.agriconnectactionneurs.exceptions.ActionneurNotFoundException;
import ag.agriconnectactionneurs.exceptions.EtatException;
import ag.agriconnectactionneurs.repositories.ActionneurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionneurService {

    private ActionneurRepository actionneurRepository;

    public ActionneurService(ActionneurRepository actionneurRepository) {
        this.actionneurRepository = actionneurRepository;
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

    /*
    public void actionner(Long id) throws ActionneurNotFoundException, EtatException {
        Actionneur actionneur = actionneurRepository.findById(id).orElseThrow(() -> new ActionneurNotFoundException());
        if(actionneur.getEtat() == EtatActionneur.ACTIVE)
            throw new EtatException("L'actionneur est déjà activé");

    }*/

}
