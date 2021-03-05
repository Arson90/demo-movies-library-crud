package net.javaapp.movies.web;

import net.javaapp.movies.dao.MovieDAO;
import net.javaapp.movies.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editServlet")
public class EditServlet extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        this.movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        Movie movie = this.movieDAO.selectMovieById(id);

        resp.getWriter().print("<form action='updateServlet' method='post'>"
                + "<table>"
                + "<tr><td></td><td><input type='hidden' name='id' value='" + movie.getId() + "'/></td></tr>"
                + "<tr><td>Title:</td><td><input type='text' name='title' value='" + movie.getTitle() + "'/></td></tr>"
                + "<tr><td>Director:</td><td><input type='text' name='director' value='" + movie.getDirector() + "'/></td></tr>"
                + "<tr><td>Price:</td><td><input type='text' name='price' value='" + movie.getPrice() + "'/></td></tr>"
                + "<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>"
                + "</table>"
                + "</form>");
    }
}
