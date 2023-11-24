package flow.flowtask.service;

import flow.flowtask.domain.FixedExtension;
import flow.flowtask.repository.FixedExtensionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FixedExtensionService {

    private final FixedExtensionsRepository fixedExtensionsRepository;

    @Autowired
    public FixedExtensionService(FixedExtensionsRepository fixedExtensionsRepository) {
        this.fixedExtensionsRepository = fixedExtensionsRepository;
    }

    public boolean isFixedExtensionDuplicate(String ceName) {
        // 고정 확장자 테이블에서 모든 확장자 가져오기
        List<FixedExtension> fixedExtensions = fixedExtensionsRepository.findAll();

        // 고정확장자와의 중복 체크
        for (FixedExtension fixedExtension : fixedExtensions) { // <타입> 반복될때의 변수 : list자체
            if (ceName.equals(fixedExtension.getFeName())) {

                return true; // 중복
            }
        }

        return false; // 중복 없음
    }


    public String toggleCheck(String feName, boolean isChecked) {

        // 고정 확장자 찾기
        FixedExtension fixedExtension = fixedExtensionsRepository.findByFeName(feName);

        String tn = null;

        if (fixedExtension != null) {

            System.out.println("클릭한 확장자 : " + fixedExtension.toString());

            // 엔티티 필드값 변경
            fixedExtension.setFeChecked(isChecked); //

            System.out.println("set함" + fixedExtension);

            // DB 저장 -> Transactionl commit
            fixedExtensionsRepository.save(fixedExtension);

            System.out.println("save함" + fixedExtension);

            tn = fixedExtension.getFeName();

            fixedExtensionsRepository.findAll();

            System.out.println("tn : " + tn);

            System.out.println("업데이트된 확장자 : " + fixedExtension.toString());

        }

        return tn;
    }



}
