package flow.flowtask.service;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.repository.CustomExtensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CustomExtensionService {

    private final CustomExtensionsRepository customExtensionsRepository;

    @Autowired
    public CustomExtensionService(CustomExtensionsRepository customExtensionsRepository) {
        this.customExtensionsRepository = customExtensionsRepository;
    }

    /**
     * 고정 확장자 비교
     * @param ceName
     * @return
     */
    public boolean isCustomExtensionDuplicate(String ceName) {

        List<CustomExtension> customExtensions = customExtensionsRepository.findAll();

        // 고정확장자와의 중복 체크
        for (CustomExtension customExtension : customExtensions) { // <타입> 반복될때의 변수 : list 이름
            if (ceName.equals(customExtension.getCeName())) {

                return true; // 중복
            }
        }
        return false;
    }

    /**
     * 커스텀 확장자 삽입
     * @param customExtension
     */
    public void insertCtExtension(CustomExtension customExtension) {

        customExtensionsRepository.save(customExtension);

    }

    /**
     * 커스텀 확장자 Count(*)
     * @return
     */
    public Long getCustomExtensionCount() {
        return customExtensionsRepository.count();
    }

    /**
     * 커스텀 확장자 삭제
     * @param ceName
     * @return
     */
    public boolean deleteCtExtension(String ceName) {
        try {
            customExtensionsRepository.deleteByCeName(ceName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 커스텀 확장자 name 다 가져오기
     * @return
     */
    public List<String> getAllExtensions() {

        List<CustomExtension> customExtensions = customExtensionsRepository.findAll();

        List<String> extensionNames = new ArrayList<>();

        for (CustomExtension customExtension : customExtensions) {
            extensionNames.add(customExtension.getCeName());
        }

        return extensionNames;
    }
}

