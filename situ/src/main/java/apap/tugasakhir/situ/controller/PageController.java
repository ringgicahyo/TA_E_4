package apap.tugasakhir.situ.controller;

import apap.tugasakhir.situ.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }
}
