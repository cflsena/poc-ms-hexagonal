package br.com.oneos.db.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name = "tb_person")
@Audited
@AuditTable("tb_person_aud")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ExampleModel implements Serializable {

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
