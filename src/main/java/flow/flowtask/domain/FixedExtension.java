package flow.flowtask.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "FIXED_EXTENSIONS")
@Getter
@Setter
@ToString
public class FixedExtension {

    @Id
    @Column(name = "FE_EXTENSION_NAME")
    private String extensionName;

    @Column(name = "FE_CHECKED_FL")
    private boolean isFeChecked;

}
