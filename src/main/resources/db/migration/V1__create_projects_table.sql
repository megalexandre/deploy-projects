-- Flyway migration: create projects table
-- Version: 1

CREATE TABLE IF NOT EXISTS projects (
  id VARCHAR(255) PRIMARY KEY,
  client_id VARCHAR(255) NOT NULL,
  utility_company VARCHAR(255) NOT NULL,
  utility_protocol VARCHAR(255) NOT NULL,
  customer_class VARCHAR(255) NOT NULL,
  integrator VARCHAR(255) NOT NULL,
  modality VARCHAR(255) NOT NULL,
  framework VARCHAR(255) NOT NULL,
  dc_protection VARCHAR(255),
  system_power DOUBLE PRECISION,
  created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
  updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- Optional index on client_id for faster lookups
CREATE INDEX IF NOT EXISTS idx_projects_client_id ON projects (client_id);

-- Trigger to update updated_at on row update (PostgreSQL)
CREATE OR REPLACE FUNCTION trigger_set_timestamp()
RETURNS TRIGGER AS $$
BEGIN
  NEW.updated_at = now();
  RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS set_timestamp ON projects;
CREATE TRIGGER set_timestamp
BEFORE UPDATE ON projects
FOR EACH ROW
EXECUTE FUNCTION trigger_set_timestamp();
