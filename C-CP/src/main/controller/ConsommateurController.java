package controller;

@WebServlet (
	name = "ConsommateurController",
	urlPatterns = "/consommateurr"
)
public class ConsommateurController extends UtilisateurController {

	public void consulter(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}

	public void signerContrat(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}

	public void renseignerDisponibilite(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}

	public void consulterPermanence(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}
}