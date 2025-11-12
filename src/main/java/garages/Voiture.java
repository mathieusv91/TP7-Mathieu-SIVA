package garages;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Représente une voiture qui peut être stationnée dans différents garages.
 */
@RequiredArgsConstructor
@ToString
public class Voiture {

    @Getter
    @NonNull
    private final String immatriculation;

    @ToString.Exclude // On ne veut pas afficher les stationnements dans toString
    private final List<Stationnement> myStationnements = new LinkedList<>();

    /**
     * Fait rentrer la voiture dans le garage spécifié.
     * Précondition : la voiture ne doit pas déjà être dans un garage.
     *
     * @param g le garage où la voiture va stationner
     * @throws IllegalStateException si la voiture est déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws IllegalStateException {
        if (estDansUnGarage()) {
            throw new IllegalStateException("La voiture est déjà dans un garage !");
        }
        myStationnements.add(new Stationnement(this, g));
    }

    /**
     * Fait sortir la voiture du garage où elle se trouve.
     * Précondition : la voiture doit être actuellement dans un garage.
     *
     * @throws IllegalStateException si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws IllegalStateException {
        if (!estDansUnGarage()) {
            throw new IllegalStateException("Impossible de sortir : la voiture n'est pas dans un garage.");
        }
        Stationnement dernier = myStationnements.get(myStationnements.size() - 1);
        dernier.terminer();
    }

    /**
     * Retourne l'ensemble des garages visités par cette voiture.
     *
     * @return un ensemble contenant les garages visités
     */
    public Set<Garage> garagesVisites() {
        Set<Garage> garages = new HashSet<>();
        for (Stationnement s : myStationnements) {
            garages.add(s.getGarageVisite());
        }
        return garages;
    }

    /**
     * Indique si la voiture est actuellement dans un garage.
     *
     * @return true si la voiture est dans un garage, false sinon
     */
    public boolean estDansUnGarage() {
        if (myStationnements.isEmpty()) {
            return false;
        }
        Stationnement dernier = myStationnements.get(myStationnements.size() - 1);
        return dernier.estEnCours();
    }

    /**
     * Affiche la liste des stationnements regroupés par garage.
     */
    public void imprimeStationnements(PrintStream out) {
        Map<Garage, List<Stationnement>> map = new LinkedHashMap<>();
        for (Stationnement s : myStationnements) {
            map.computeIfAbsent(s.getGarageVisite(), k -> new ArrayList<>()).add(s);
        }

        for (Map.Entry<Garage, List<Stationnement>> entry : map.entrySet()) {
            out.println("Garage " + entry.getKey() + " :");
            for (Stationnement s : entry.getValue()) {
                out.println("\t- " + s);
            }
        }
    }
}

