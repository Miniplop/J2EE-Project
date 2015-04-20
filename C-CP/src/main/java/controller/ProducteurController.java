package java.controller;

@WebServlet (
	name = "ProducteurController",
	urlPatterns = "/producteur"
)
public class ProducteurController extends UtilisateurController {

	public void consulter(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}

	public void renseignerProduit(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}

	public void validerContrat(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}
}