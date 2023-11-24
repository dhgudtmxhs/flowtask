package flow.flowtask.controller;

import flow.flowtask.service.FixedExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fixed-extensions")
public class FixedExtensionController {

    private final FixedExtensionService feExtensionService;

    @Autowired
    public FixedExtensionController(FixedExtensionService feExtensionService) {
        this.feExtensionService = feExtensionService;
    }
    @PostMapping("/toggle-check")
    public ResponseEntity<Map<String, Boolean>> toggleCheck(@RequestBody Map<String, Object> requestBody) {
        try {
            String feName = (String) requestBody.get("feName");
            boolean isChecked = (boolean) requestBody.get("isChecked");

            System.out.println("컨트롤러 도착");
            feExtensionService.toggleCheck(feName, isChecked);
            System.out.println("컨트롤러 나감");

            // Create a response map
            Map<String, Boolean> responseMap = new HashMap<>();
            responseMap.put("isChecked", isChecked);

            // Send the response
            return ResponseEntity.ok(responseMap);
        } catch (Exception e) {
            // Log the exception or perform appropriate handling
            e.printStackTrace();
            // Return an error response to the client if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}