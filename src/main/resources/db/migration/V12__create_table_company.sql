CREATE TABLE IF NOT EXISTS company (
   id UUID PRIMARY KEY,
   name VARCHAR(256) NOT NULL,
   CONSTRAINT company_name_unique UNIQUE (name)
);

CREATE INDEX IF NOT EXISTS idx_company_name ON company(name);