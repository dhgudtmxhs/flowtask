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

    // 체크박스 값 삽입, check 값 설정
    @PostMapping("insert-fe-extension")
    public ResponseEntity<Map<String, Boolean>> insertFeExtension(@RequestBody Map<String, Object> requestBody) {
        try {

            String feName = (String) requestBody.get("feName");
            boolean isChecked = (boolean) requestBody.get("isChecked");

            feExtensionService.insertFeExtension(feName, isChecked);

            Map<String, Boolean> responseMap = new HashMap<>();
            responseMap.put("isChecked", isChecked);

            return ResponseEntity.ok(responseMap); //

        } catch (Exception e) {

            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}