package tacos.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping
    public String errorPage(HttpServletResponse http) {
        System.out.println("http status : " + http.getStatus());
        int statusCode = http.getStatus();
        if ( statusCode == 403 )
            return "error403";
        if ( statusCode == 404 )
            return "error404";
        return "error";
    }
}
