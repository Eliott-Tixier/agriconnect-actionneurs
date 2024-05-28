package ag.agriconnectactionneurs.controllers;

import ag.agriconnectactionneurs.entities.Actionneur;
import ag.agriconnectactionneurs.exceptions.ActionneurNotFoundException;
import ag.agriconnectactionneurs.services.ActionneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actionneur")
public class ActionneurController {

    private ActionneurService actionneurService;

    public ActionneurController(ActionneurService actionneurService) {
        this.actionneurService = actionneurService;
    }

    @PostMapping
    public Actionneur save(@RequestBody Actionneur actionneur){
        return actionneurService.save(actionneur);
    }

    @PutMapping()
    public Actionneur update(@RequestBody Actionneur actionneur) throws ActionneurNotFoundException {
        return actionneurService.update(actionneur.getId(), actionneur);
    }

    @GetMapping("/{id}")
    public Actionneur getActionneur(@RequestParam Long id) throws ActionneurNotFoundException {
        return actionneurService.getActionneur(id);
    }

    @GetMapping
    public List<Actionneur> getAllActionneurs(){
        return actionneurService.getAllActionneurs();
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestParam Long id) throws ActionneurNotFoundException {
        actionneurService.deleteActionneur(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerActionneurNotFoundException(ActionneurNotFoundException e){
        return e.getMessage() +"\n"+e.getStackTrace();
    }

}
