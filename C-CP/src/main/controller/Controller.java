package controller;

public abstract class Controller {
	@Resource(name = "jdbc/cooperative")
	private DataSource ds;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		throw new UnsupportedOperationException();
	}

	public void consulter(HttpServletRequest request, HttpServletResponse response) {
		throw new UnsupportedOperationException();
	}
}