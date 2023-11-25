package flow.flowtask.controller;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.domain.FileExtension;
import flow.flowtask.service.CustomExtensionService;
import flow.flowtask.service.FileExtensionService;
import flow.flowtask.service.FixedExtensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/custom-extensions")
public class CustomExtensionController {

    private final CustomExtensionService customExtensionService;
    private final FixedExtensionService feExtensionService;
    private final FileExtensionService fileExtensionService;

    @Autowired
    public CustomExtensionController(CustomExtensionService customExtensionService, FixedExtensionService feExtensionService, FileExtensionService fileExtensionService) {
        this.customExtensionService = customExtensionService;
        this.feExtensionService = feExtensionService;
        this.fileExtensionService = fileExtensionService;
    }

    // 커스텀 확장자 삽입
    @PostMapping("/insert-ct-extension")
    public ResponseEntity<String> insertCtExtension(@RequestParam String ceName) {
        System.out.println("확장자 이름은 " + ceName);

        // 고정 확장자와의 비교
        if (feExtensionService.FeDuplicate(ceName)) {

            return ResponseEntity.badRequest().body("고정확장자는 체크박스로 체크해주세요.");

        } else {

            // 커스텀 확장자와의 비교
            if (customExtensionService.isCustomExtensionDuplicate(ceName)) {

                return ResponseEntity.badRequest().body("이미 존재하는 커스텀 확장자입니다.");

            } else { // 조건 다 통과한 경우

                CustomExtension customExtension = new CustomExtension();
                customExtension.setCeName(ceName);

                FileExtension fileExtension = new FileExtension();
                fileExtension.setFileName(ceName);

                // 커스텀 확장자 삽입
                customExtensionService.insertCtExtension(customExtension);

                // 파일 확장자 삽입
                fileExtensionService.insertCtExtension(fileExtension);

                return ResponseEntity.ok("확장자가 성공적으로 삽입되었습니다.");

            }
        }
    }

    // 커스텀 확장자 count(*)
    @GetMapping("/count")
    public ResponseEntity<Long> getCustomExtensionCount() {

        Long count = customExtensionService.getCustomExtensionCount();

        return ResponseEntity.ok(count);

    }

    // 커스텀 확장자 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCtExtension(@RequestParam String ceName) {
        // 확장자 삭제 로직 구현
        System.out.println("cename: " + ceName);

        boolean deleteFl = customExtensionService.deleteCtExtension(ceName);

        if (deleteFl) {

            fileExtensionService.deleteFileExtension(ceName);

            return ResponseEntity.ok("확장자가 성공적으로 삭제되었습니다.");

        } else {

            return ResponseEntity.badRequest().body("확장자 삭제 중에 오류가 발생했습니다.");

        }

    }

}



