package modele;

import java.util.ArrayList;
import modele.Contrat;

public class Consommateur extends Utilisateur {
	private Permanence permanence;
	private ArrayList<Contrat> contrats = new ArrayList<Contrat>();
}