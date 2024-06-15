package ag.agriconnectactionneurs.entities;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Historique {

    private Long idActionneur;

    private LocalDate date;

    private  Long duree;
}