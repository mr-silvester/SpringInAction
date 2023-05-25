package tacos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user")
    public String userPage(Model model, HttpSession httpSession, Authentication authentication) {
        setRoleAttribute(httpSession, authentication);
        model.addAttribute("userName", authentication.getName());
        model.addAttribute("isAdmin", httpSession.getAttribute("isAdmin"));
        if  ( !authentication.isAuthenticated() )
            return "login";
        return "my-page";
    }

    private void setRoleAttribute(HttpSession httpSession, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        httpSession.setAttribute("isAdmin", isAdmin);
        assert authentication != null;
        httpSession.setAttribute("userName", authentication.getName());
    }
}
