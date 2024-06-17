package ag.agriconnectactionneurs;

import ag.agriconnectactionneurs.entities.Actionneur;
import ag.agriconnectactionneurs.entities.EtatActionneur;
import ag.agriconnectactionneurs.repositories.ActionneurRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AgriconnectActionneursApplication implements CommandLineRunner {

    private final ActionneurRepository actionneurRepository;

    public AgriconnectActionneursApplication(ActionneurRepository actionneurRepository) {
        this.actionneurRepository = actionneurRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AgriconnectActionneursApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Actionneur actionneur1 = new Actionneur();
        actionneur1.setEtat(EtatActionneur.DESACTIVE);
        actionneur1.setLongitude(10.0);
        actionneur1.setLatitude(20.0);
        actionneur1.setIdUtilisateur(1L);

        Actionneur actionneur2 = new Actionneur();
        actionneur2.setEtat(EtatActionneur.DESACTIVE);
        actionneur2.setLongitude(11.0);
        actionneur2.setLatitude(21.0);
        actionneur2.setIdUtilisateur(1L);

        Actionneur actionneur3 = new Actionneur();
        actionneur3.setEtat(EtatActionneur.DESACTIVE);
        actionneur3.setLongitude(12.0);
        actionneur3.setLatitude(22.0);
        actionneur3.setIdUtilisateur(1L);

        Actionneur actionneur4 = new Actionneur();
        actionneur4.setEtat(EtatActionneur.DESACTIVE);
        actionneur4.setLongitude(13.0);
        actionneur4.setLatitude(23.0);
        actionneur4.setIdUtilisateur(1L);

        Actionneur actionneur5 = new Actionneur();
        actionneur5.setEtat(EtatActionneur.DESACTIVE);
        actionneur5.setLongitude(14.0);
        actionneur5.setLatitude(24.0);
        actionneur5.setIdUtilisateur(2L);

        Actionneur actionneur6 = new Actionneur();
        actionneur6.setEtat(EtatActionneur.DESACTIVE);
        actionneur6.setLongitude(15.0);
        actionneur6.setLatitude(25.0);
        actionneur6.setIdUtilisateur(2L);

        Actionneur actionneur7 = new Actionneur();
        actionneur7.setEtat(EtatActionneur.DESACTIVE);
        actionneur7.setLongitude(16.0);
        actionneur7.setLatitude(26.0);
        actionneur7.setIdUtilisateur(2L);

        Actionneur actionneur8 = new Actionneur();
        actionneur8.setEtat(EtatActionneur.DESACTIVE);
        actionneur8.setLongitude(17.0);
        actionneur8.setLatitude(27.0);
        actionneur8.setIdUtilisateur(2L);

        Actionneur actionneur9 = new Actionneur();
        actionneur9.setEtat(EtatActionneur.DESACTIVE);
        actionneur9.setLongitude(18.0);
        actionneur9.setLatitude(28.0);
        actionneur9.setIdUtilisateur(3L);

        Actionneur actionneur10 = new Actionneur();
        actionneur10.setEtat(EtatActionneur.DESACTIVE);
        actionneur10.setLongitude(19.0);
        actionneur10.setLatitude(29.0);
        actionneur10.setIdUtilisateur(3L);

        Actionneur actionneur11 = new Actionneur();
        actionneur11.setEtat(EtatActionneur.DESACTIVE);
        actionneur11.setLongitude(20.0);
        actionneur11.setLatitude(30.0);
        actionneur11.setIdUtilisateur(3L);

        Actionneur actionneur12 = new Actionneur();
        actionneur12.setEtat(EtatActionneur.DESACTIVE);
        actionneur12.setLongitude(21.0);
        actionneur12.setLatitude(31.0);
        actionneur12.setIdUtilisateur(3L);

        List<Actionneur> actionneurs = Arrays.asList(actionneur1, actionneur2, actionneur3, actionneur4, actionneur5, actionneur6, actionneur7, actionneur8, actionneur9, actionneur10, actionneur11, actionneur12);
        actionneurRepository.saveAll(actionneurs);
    }
}
