--liquibase formatted sql
--changeset cleiton.sena:1.0

CREATE TABLE tb_person (
  id BINARY(16) NOT NULL,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT pk_tb_person PRIMARY KEY (id)
);

--rollback drop table `tb_person`