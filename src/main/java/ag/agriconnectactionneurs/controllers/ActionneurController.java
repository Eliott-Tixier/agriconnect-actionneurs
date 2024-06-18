package ag.agriconnectactionneurs.controllers;

import ag.agriconnectactionneurs.entities.Actionneur;
import ag.agriconnectactionneurs.exceptions.ActionneurNotFoundException;
import ag.agriconnectactionneurs.services.ActionneurService;
import ag.agriconnectactionneurs.websocket.ActionneurWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.WebSocketSession;

import java.util.List;

@RestController
@RequestMapping("/api/actionneurs")
public class ActionneurController {

    private ActionneurService actionneurService;
    private final ActionneurWebSocketHandler webSocketHandler;

    public ActionneurController(ActionneurService actionneurService, ActionneurWebSocketHandler webSocketHandler) {
        this.actionneurService = actionneurService;
        this.webSocketHandler = webSocketHandler;
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
    public Actionneur getActionneur(@PathVariable Long id) throws ActionneurNotFoundException {
        return actionneurService.getActionneur(id);
    }

    @GetMapping
    public List<Actionneur> getAllActionneurs(){
        return actionneurService.getAllActionneurs();
    }

    @GetMapping("idUtilisateur/{id}")
    public List<Actionneur> getActionneurByIdUtilisateur(@PathVariable Long id) throws ActionneurNotFoundException {
        return actionneurService.findActionneurByIdUtilisateur(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws ActionneurNotFoundException {
        actionneurService.deleteActionneur(id);
    }

    @PostMapping("/trigger/{id}")
    public ResponseEntity<String> triggerActionneur(@PathVariable Long id, @RequestParam Long duration) {
        try {
            actionneurService.triggerActionneur(id, duration);
            webSocketHandler.notifyClient(id, "Actionneur activé pour " + duration + " millisecondes.");
            return ResponseEntity.ok("Actionneur #" + id + " activé pour " + duration + " millisecondes.");
        } catch (ActionneurNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erreur lors de l'activation de l'actionneur : " + e.getMessage());
        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlerActionneurNotFoundException(ActionneurNotFoundException e){
        return e.getMessage() +"\n"+e.getStackTrace();
    }

}
