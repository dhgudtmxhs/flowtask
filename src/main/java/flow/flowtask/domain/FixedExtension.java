package flow.flowtask.domain;


import jakarta.persistence.*;
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
    private String feName;

    @Column(name = "FE_CHECKED_FL")
    private boolean feChecked;

}
