package flow.flowtask.controller;

import flow.flowtask.domain.FileExtension;
import flow.flowtask.service.FileExtensionService;
import flow.flowtask.service.FixedExtensionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/file-extensions")
public class FileExtensionController {

    private final FileExtensionService fileExtensionService;

    public FileExtensionController(FileExtensionService fileExtensionService) {
        this.fileExtensionService = fileExtensionService;
    }


    // 차단된 확장자명 다 가져오기
    @GetMapping("/get-allowed-extensions")
    public ResponseEntity<Map<String, String>> getAllowedExtensions() {

        Bcrypt

        List<FileExtension> fileExtensions = fileExtensionService.getAllowedExtensions();

        StringBuilder extensionsStringBuilder = new StringBuilder();
        for (FileExtension fileExtension : fileExtensions) {
            extensionsStringBuilder.append(fileExtension.getFileName()).append("|");
        }

        String allowedExtensions = extensionsStringBuilder.toString().replaceAll("\\|$", "");

        Map<String, String> response = Collections.singletonMap("allowedExtensions", allowedExtensions);

        System.out.println(response);

        return ResponseEntity.ok(response);

    }

}
