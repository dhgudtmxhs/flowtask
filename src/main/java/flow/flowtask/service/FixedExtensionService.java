package flow.flowtask.service;

import flow.flowtask.domain.FileExtension;
import flow.flowtask.domain.FixedExtension;
import flow.flowtask.repository.FileExtensionRepository;
import flow.flowtask.repository.FixedExtensionsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FixedExtensionService {

    private final FixedExtensionsRepository fixedExtensionsRepository;
    private final FileExtensionRepository fileExtensionRepository;

    public FixedExtensionService(FixedExtensionsRepository fixedExtensionsRepository, FileExtensionRepository fileExtensionRepository) {
        this.fixedExtensionsRepository = fixedExtensionsRepository;
        this.fileExtensionRepository = fileExtensionRepository;
    }

    // 고정 확장자 전부 가져와서 커스텀 확장자와 비교
    public boolean FeDuplicate(String ceName) {

        List<FixedExtension> fixedExtensions = fixedExtensionsRepository.findAll();

        // 고정확장자와의 중복 체크
        for (FixedExtension fixedExtension : fixedExtensions) { // <타입> 반복될때의 변수 : list자체
            if (ceName.equals(fixedExtension.getFeName())) {

                return true; // 중복
            }
        }

        return false; // 중복 없음
    }

    // 고정 확장자 삽입
    public void insertFeExtension(String feName, boolean isChecked) {

        // 고정 확장자 찾기
        FixedExtension fixedExtension = fixedExtensionsRepository.findByFeName(feName);

        if (fixedExtension != null) {

            System.out.println("클릭한 확장자 : " + fixedExtension.toString());

            fixedExtension.setFeChecked(isChecked); // 객체에 set

            fixedExtensionsRepository.save(fixedExtension); // fe db에 update

            String trueFeName = fixedExtension.getFeName();

            if(isChecked){

                FileExtension fileExtension = new FileExtension();
                fileExtension.setFileName(trueFeName);

                fileExtensionRepository.save(fileExtension);

            }else{
                fileExtensionRepository.deleteByFileName(trueFeName);
            }
        }
    }
}
