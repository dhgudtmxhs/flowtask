package flow.flowtask.controller;

import flow.flowtask.service.CustomExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final CustomExtensionService customExtensionService;

    public HomeController(CustomExtensionService customExtensionService) {
        this.customExtensionService = customExtensionService;
    }

    @GetMapping("/")
    public String home(Model model) {

        //
        List<String> extensions = customExtensionService.getAllExtensions();

        Long firstCount = customExtensionService.getCustomExtensionCount();

        model.addAttribute("customExtensions", extensions);
        model.addAttribute("firstCount", firstCount);

        return "flowTask"; // view
    }
}