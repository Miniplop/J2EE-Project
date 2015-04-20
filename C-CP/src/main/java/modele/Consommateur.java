package java.modele;

import java.util.ArrayList;
import java.modele.Contrat;

public class Consommateur extends Utilisateur {
	private Permanence permanence;
	private ArrayList<Contrat> contrats = new ArrayList<Contrat>();
}