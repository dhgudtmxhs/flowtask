package flow.flowtask.service;

import flow.flowtask.domain.FileExtension;
import flow.flowtask.repository.FileExtensionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class FileExtensionService {

    private final FileExtensionRepository fileExtensionRepository;
    public FileExtensionService(FileExtensionRepository fileExtensionRepository) {
        this.fileExtensionRepository = fileExtensionRepository;
    }

    /**
     * 차단된 커스텀 확장자를 파일 확장자에 저장
     * @param fileExtension
     */
    public void insertCtExtension(FileExtension fileExtension) {
        fileExtensionRepository.save(fileExtension);
    }

    /**
     * 파일확장자에서 삭제(고정,커스텀)
     * @param ceName
     */
    public void deleteFileExtension(String ceName) {

    fileExtensionRepository.deleteByFileName(ceName);

    }

    /**
     * 파일 확장자 컬럼 값들 가져오기
     * @return List
     */
    public List<FileExtension> getAllowedExtensions() {

        return fileExtensionRepository.findAll();
    }
}
