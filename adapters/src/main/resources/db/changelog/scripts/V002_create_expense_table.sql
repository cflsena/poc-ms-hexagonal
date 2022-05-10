--liquibase formatted sql
--changeset cleiton.sena:1.1

CREATE TABLE tb_expense (
  id BINARY(16) NOT NULL,
  description VARCHAR(255) NOT NULL,
  created_at datetime NOT NULL,
  expense_value DECIMAL NOT NULL,
  tags VARCHAR(255) NULL,
  person_id BINARY(16) NOT NULL,
  CONSTRAINT pk_tb_expense PRIMARY KEY (id)
);

ALTER TABLE tb_expense ADD CONSTRAINT FK_TB_EXPENSE_ON_PERSON FOREIGN KEY (person_id) REFERENCES tb_person (id);

--rollback drop table `tb_expense`