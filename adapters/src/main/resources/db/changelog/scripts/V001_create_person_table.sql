--liquibase formatted sql
--changeset cleiton.sena:V001_create_person_table

CREATE TABLE tb_example (
  id BINARY(16) NOT NULL,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_tb_person_id PRIMARY KEY (id)
);

--rollback drop table `tb_person`