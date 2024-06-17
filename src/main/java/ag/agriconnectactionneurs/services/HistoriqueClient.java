package ag.agriconnectactionneurs.services;

import ag.agriconnectactionneurs.entities.Historique;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient("achistoriques")
public interface HistoriqueClient {

    @RequestMapping(value = "/api/historiques", method = RequestMethod.POST)
    Historique createHistorique(@RequestBody Historique historique);
}
