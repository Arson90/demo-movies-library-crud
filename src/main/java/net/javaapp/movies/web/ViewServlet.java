package net.javaapp.movies.web;

import net.javaapp.movies.dao.MovieDAO;
import net.javaapp.movies.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewServlet")
public class ViewServlet extends HttpServlet {
    private MovieDAO movieDAO;

    @Override
    public void init() throws ServletException {
        this.movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Movie> movies = this.movieDAO.selectAllMovies();

        resp.setContentType("text/html");
        resp.getWriter().print("<a href='index.html'>Add new movie</a>"
                + "<h1>Movies list</h1>"
                + "<table border='1' width='100%'>"
                + "<tr><th>Id</th><th>Title</th><th>Director</th><th>Price</th>"
                + "<th>Edit</th><th>Delete</th></tr>");

        for (Movie m : movies){
            resp.getWriter().print("<tr><td>" + m.getId() + "</td><td>"
                    + m.getTitle() + "</td><td>"
                    + m.getDirector() + "</td><td>"
                    + m.getPrice() + "$" + "</td><td>"
                    + "<a href='editServlet?id=" + m.getId() + "'>edit</a></td><td>"
                    + "<a href='deleteServlet?id=" + m.getId() + "'>delete</a></td></tr>");
        }
        resp.getWriter().print("</table>");
    }
}
