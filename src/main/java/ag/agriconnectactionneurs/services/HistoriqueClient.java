package ag.agriconnectactionneurs.services;

import ag.agriconnectactionneurs.entities.Historique;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("achistoriques")
public interface HistoriqueClient {

    @PostMapping("/api/historiques")
    Historique createHistorique(@RequestBody Historique historique);
}
