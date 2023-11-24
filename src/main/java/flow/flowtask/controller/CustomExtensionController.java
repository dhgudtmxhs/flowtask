package flow.flowtask.controller;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.service.CustomExtensionService;
import flow.flowtask.service.FixedExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/custom-extensions")
public class CustomExtensionController {

    private final CustomExtensionService customExtensionService;
    private final FixedExtensionService feExtensionService;

    @Autowired
    public CustomExtensionController(CustomExtensionService customExtensionService, FixedExtensionService feExtensionService) {
        this.customExtensionService = customExtensionService;
        this.feExtensionService = feExtensionService;
    }

    @PostMapping("/toggle-check")
    public ResponseEntity<String> insertCtExtension(@RequestParam String ceName) {
        System.out.println("확장자 이름은 " + ceName);

        if (feExtensionService.isFixedExtensionDuplicate(ceName)) {

            return ResponseEntity.badRequest().body("고정확장자는 체크박스로 체크해주세요.");

        } else {

            if (customExtensionService.isCustomExtensionDuplicate(ceName)) {

                return ResponseEntity.badRequest().body("이미 존재하는 커스텀 확장자입니다.");

            } else {

                CustomExtension customExtension = new CustomExtension();
                customExtension.setCeName(ceName);

                customExtensionService.insertCtExtension(customExtension);

                return ResponseEntity.ok("확장자가 성공적으로 삽입되었습니다.");

            }
        }
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCustomExtensionCount() {

        Long count = customExtensionService.getCustomExtensionCount();

        return ResponseEntity.ok(count);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCtExtension(@RequestParam String ceName) {
        // 확장자 삭제 로직 구현
        System.out.println("cename: " + ceName);
        System.out.println("일단 옴");

        boolean deleteFl = customExtensionService.deleteCtExtension(ceName);

        System.out.println("서비스 갔다옴");

        if (deleteFl) {
            return ResponseEntity.ok("확장자가 성공적으로 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("확장자 삭제 중에 오류가 발생했습니다.");
        }
    }

}



