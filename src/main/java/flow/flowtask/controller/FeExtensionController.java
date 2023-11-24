package flow.flowtask.controller;

import flow.flowtask.domain.FixedExtension;
import flow.flowtask.service.FeExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fixed-extensions")
public class FeExtensionController {

    private final FeExtensionService feExtensionService;

    @Autowired
    public FeExtensionController(FeExtensionService feExtensionService) {
        this.feExtensionService = feExtensionService;
    }

    @PostMapping("/toggle-check")
    public void toggleCheck(@RequestParam String extensionName, @RequestBody boolean isChecked) {
        System.out.println("컨트롤러까지옴");
        feExtensionService.toggleCheck(extensionName, isChecked);
    }
}
