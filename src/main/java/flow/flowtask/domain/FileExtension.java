package flow.flowtask.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "FILE_EXTENSIONS")
@Getter
@Setter
@ToString
public class FileExtension {

    @Id
    @Column(name = "FILE_EXTENSION_NAME")
    private String fileName;

}
