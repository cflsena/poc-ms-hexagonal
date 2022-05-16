--liquibase formatted sql
--changeset cleiton.sena:V002_create_person_auditing_table

CREATE TABLE tb_example_aud
(
    id              BINARY(16) NOT NULL,
    revision        int NOT NULL,
    revision_type   TINYINT,
    name            VARCHAR(255),

    CONSTRAINT pk_tb_person_aud_id_rev PRIMARY KEY (id, revision),
    CONSTRAINT fk_revinfo_rev FOREIGN KEY (revision) REFERENCES revinfo(id)
);

--rollback drop table `tb_person_aud`