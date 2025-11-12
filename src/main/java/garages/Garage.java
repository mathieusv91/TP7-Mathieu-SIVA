package garages;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Représente un garage où les voitures peuvent être stationnées.
 */
@Getter // Génère les getters pour tous les champs
@ToString // Génère la méthode toString()
@RequiredArgsConstructor // Génère un constructeur avec tous les champs "final" ou annotés avec @NonNull
public class Garage {

	/**
	 * Le nom du garage.
	 * Ce champ ne peut pas être null.
	 */
	@NonNull
	@Setter // Génère le setter pour ce champ
	private String name;

}
