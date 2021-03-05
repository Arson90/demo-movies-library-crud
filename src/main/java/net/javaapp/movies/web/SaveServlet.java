package net.javaapp.movies.web;

import net.javaapp.movies.dao.MovieDAO;
import net.javaapp.movies.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    private MovieDAO movieDAO;

    public void init() {
        this.movieDAO = new MovieDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String title = req.getParameter("title");
        String director = req.getParameter("director");
        Float price = Float.parseFloat(req.getParameter("price"));
        Movie movie = new Movie(title, director, price);
        boolean result = this.movieDAO.insertMovie(movie);

        if (result){
            resp.getWriter().print("<p>Record has been added successfully!<p>");
            req.getRequestDispatcher("index.html").include(req, resp);
        } else {
                resp.getWriter().print("Sorry, There was no added any record!");
        }
    }
}
