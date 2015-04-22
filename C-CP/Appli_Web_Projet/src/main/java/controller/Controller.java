package controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public abstract class Controller extends HttpServlet {
    
	@Resource(name = "jdbc/cooperative")
	protected DataSource ds;

        @Override
	public abstract void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;
}