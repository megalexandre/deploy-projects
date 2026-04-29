-- Create concessionaires table (utility companies)
CREATE TABLE IF NOT EXISTS concessionaires (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    code VARCHAR(50),
    region VARCHAR(100),
    phone VARCHAR(20),
    email VARCHAR(255),
    active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT concessionaire_name_unique UNIQUE (name)
);

-- Insert some common Brazilian concessionaires
INSERT INTO concessionaires (id, name, code, region) VALUES
    (gen_random_uuid(), 'Enel', 'ENEL', 'SP'),
    (gen_random_uuid(), 'CEMIG', 'CEMIG', 'MG'),
    (gen_random_uuid(), 'Copel', 'COPEL', 'PR'),
    (gen_random_uuid(), 'CPFL', 'CPFL', 'SP'),
    (gen_random_uuid(), 'Light', 'LIGHT', 'RJ'),
    (gen_random_uuid(), 'Energisa', 'ENERGISA', 'Multiple')
ON CONFLICT (name) DO NOTHING;

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_concessionaires_name ON concessionaires(name);
CREATE INDEX IF NOT EXISTS idx_concessionaires_code ON concessionaires(code);
CREATE INDEX IF NOT EXISTS idx_concessionaires_active ON concessionaires(active);

-- Add comments
COMMENT ON TABLE concessionaires IS 'Stores utility companies (concessionárias)';
COMMENT ON COLUMN concessionaires.code IS 'Short code identifier for the concessionaire';
COMMENT ON COLUMN concessionaires.region IS 'Geographic region where the concessionaire operates';

