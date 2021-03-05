package net.javaapp.movies.web;

import net.javaapp.movies.dao.MovieDAO;
import net.javaapp.movies.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateServlet")
public class UpdateServlet extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        this.movieDAO = new MovieDAO();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        String director = req.getParameter("director");
        Float price = Float.parseFloat(req.getParameter("price"));

        Movie movie = new Movie();
        movie.setId(id);
        movie.setTitle(title);
        movie.setDirector(director);
        movie.setPrice(price);

        boolean result = this.movieDAO.updateMovie(movie);

        if (result){
            resp.getWriter().print("Record has been updated successfully");
            resp.sendRedirect("viewServlet");
        } else {
            resp.getWriter().print("Sorry! Record hasn't been updated...");
        }
    }
}
