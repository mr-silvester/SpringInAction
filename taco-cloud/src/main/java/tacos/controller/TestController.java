package tacos.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping
    @PreAuthorize("@customPermissionEvaluator.hasPermission(authentication, #a, 'isAdmin')")    // hasPermission 을 이용한 권한검사
    public String testPage(Long a) {
        return "test";
    }
}
