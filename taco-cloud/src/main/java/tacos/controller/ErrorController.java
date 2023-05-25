package tacos.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping
    public String errorPage(HttpServletResponse http) {
        //http 응답 코드 추출
        int statusCode = http.getStatus();
        // 403 에러 시 error 403.html 호출
        if ( statusCode == 403 )
            return "error403";
        // 404 에러 시 error 404.html 호출
        if ( statusCode == 404 )
            return "error404";
        return "error";
    }
}
