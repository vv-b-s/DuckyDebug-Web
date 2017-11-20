package duckydebug.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EulaController {
    @GetMapping("/eula")
    public String getEula(Model model){
        model.addAttribute("toolbarPageName", "End User Licence Agreement");
        model.addAttribute("view","views/eula");
        return "base-layout";
    }

}
