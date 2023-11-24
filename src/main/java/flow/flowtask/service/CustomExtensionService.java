package flow.flowtask.service;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.repository.CustomExtensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomExtensionService {

    private final CustomExtensionsRepository customExtensionsRepository;

    @Autowired
    public CustomExtensionService(CustomExtensionsRepository customExtensionsRepository) {
        this.customExtensionsRepository = customExtensionsRepository;
    }

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

    public void insertCtExtension(CustomExtension customExtension) {

        customExtensionsRepository.save(customExtension);

    }

    public Long getCustomExtensionCount() {
        return customExtensionsRepository.count();
    }

        public boolean deleteCtExtension(String ceName) {
            try {
                customExtensionsRepository.deleteByCeName(ceName);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }



}

