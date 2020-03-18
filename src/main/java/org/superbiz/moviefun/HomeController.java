package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.transaction.Transactional;
import java.util.Map;

@Controller
@Transactional
public class HomeController {

    MoviesBean moviesBean;

    public HomeController(MoviesBean moviesBean) {
        this.moviesBean = moviesBean;
    }

    @GetMapping("/")
    public String index(Map<String, Object> model,
                        @Value("${moviefun.title:Moviefun}") String pageTitle,
                        @Value("${moviefun.version:2.0}") String appVersion) {
        model.put("PageTitle", pageTitle);
        model.put("AppVersion", appVersion);
        return "index";
    }

    @GetMapping("/setup")
    public String setup(Map<String, Object> model) throws NamingException {
        moviesBean.addMovie(new Movie("Wedding Crashers", "David Dobkin", "Comedy", 7, 2005));
        moviesBean.addMovie(new Movie("Starsky & Hutch", "Todd Phillips", "Action", 6, 2004));
        moviesBean.addMovie(new Movie("Shanghai Knights", "David Dobkin", "Action", 6, 2003));
        moviesBean.addMovie(new Movie("I-Spy", "Betty Thomas", "Adventure", 5, 2002));
        moviesBean.addMovie(new Movie("The Royal Tenenbaums", "Wes Anderson", "Comedy", 8, 2001));
        moviesBean.addMovie(new Movie("Zoolander", "Ben Stiller", "Comedy", 6, 2001));
        moviesBean.addMovie(new Movie("Shanghai Noon", "Tom Dey", "Comedy", 7, 2000));

        model.put("movies", moviesBean.getMovies());
        return "setup";
    }
}
