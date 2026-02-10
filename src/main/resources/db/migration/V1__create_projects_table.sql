CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS projects (
  id UUID PRIMARY KEY,
  client_id UUID NOT NULL,
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
