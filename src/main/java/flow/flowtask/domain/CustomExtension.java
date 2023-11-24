package flow.flowtask.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CUSTOM_EXTENSIONS")
@Getter
@Setter
@ToString
public class CustomExtension {

    @Id
    @Column(name = "CT_EXTENSION_NAME")
    private String ceName;

/*
    @Column(name = "CE_CHECKED_FL")
    private boolean ceChecked;
*/

}
