package flow.flowtask.repository;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.domain.FileExtension;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileExtensionRepository extends JpaRepository<FileExtension, String> {
    void deleteByFileName(String trueFeName);

}
