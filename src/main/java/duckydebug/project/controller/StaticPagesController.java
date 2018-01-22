package duckydebug.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * This controller is meant to redirect to the static content of the website
 */
@Controller
public class StaticPagesController {
    @GetMapping("/help")
    public String getHelp(Model model){
        return loadPage(model, "views/help","Help");
    }

    @GetMapping("/about")
    public String getAbout(Model model){
        return loadPage(model, "views/about","About");
    }

    private String loadPage(Model model, String pageLocation, String pageTitle){
        model.addAttribute("toolbarPageName", pageTitle);
        model.addAttribute("view",pageLocation);
        return "base-layout";
    }

}
