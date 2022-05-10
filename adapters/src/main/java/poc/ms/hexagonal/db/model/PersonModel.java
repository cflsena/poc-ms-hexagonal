package poc.ms.hexagonal.db.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name = "TB_PERSON")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonModel implements Serializable {

    @Serial
    private static final long serialVersionUID = -6158662308809057542L;
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @NotBlank
    @Column(name = "name")
    private String name;

}
