package tacos.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String userPage(Model model, Authentication authentication) {
        setRoleAttribute(model, authentication);
        if  ( !authentication.isAuthenticated() )
            return "login";
        return "my-page";
    }

    @GetMapping("/test")
    public String testPage() {
        return "test";
    }

    private void setRoleAttribute(Model model, Authentication authentication) {
        boolean isAdmin = authentication != null && authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
        model.addAttribute("isAdmin", isAdmin);
    }
}
