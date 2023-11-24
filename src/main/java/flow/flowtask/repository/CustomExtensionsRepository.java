package flow.flowtask.repository;

import flow.flowtask.domain.CustomExtension;
import flow.flowtask.domain.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface CustomExtensionsRepository extends JpaRepository<CustomExtension, String> {
    CustomExtension save(CustomExtension customExtension);

    void deleteByCeName(String ceName);
}