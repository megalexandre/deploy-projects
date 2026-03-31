-- Tabela de revisão para Hibernate Envers
CREATE TABLE revinfo (
    rev SERIAL PRIMARY KEY,
    revtstmp BIGINT NOT NULL
);

-- Sequence para a tabela revinfo
CREATE SEQUENCE IF NOT EXISTS revinfo_seq START WITH 1 INCREMENT BY 50;

