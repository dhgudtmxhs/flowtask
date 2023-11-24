package flow.flowtask.service;

import flow.flowtask.domain.FixedExtension;
import flow.flowtask.repository.FixedExtensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FeExtensionService {

    private final FixedExtensionsRepository fixedExtensionsRepository;

    @Autowired
    public FeExtensionService(FixedExtensionsRepository fixedExtensionsRepository) {
        this.fixedExtensionsRepository = fixedExtensionsRepository;
    }

    public void toggleCheck(String extensionName, boolean isChecked) {
        FixedExtension fixedExtension = fixedExtensionsRepository.findByExtensionName(extensionName);

        if (fixedExtension != null) {
            System.out.println("Found Extension: " + fixedExtension.toString());
            fixedExtension.setFeChecked(isChecked);
            fixedExtensionsRepository.save(fixedExtension);
            System.out.println("Updated Extension: " + fixedExtension.toString());
        }
    }


}
