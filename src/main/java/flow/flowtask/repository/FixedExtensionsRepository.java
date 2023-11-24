package flow.flowtask.repository;

import flow.flowtask.domain.FixedExtension;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FixedExtensionsRepository extends JpaRepository<FixedExtension, String> {
    FixedExtension findByFeName(String feName);

}
