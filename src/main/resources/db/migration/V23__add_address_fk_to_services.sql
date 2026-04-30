ALTER TABLE services
    ADD COLUMN IF NOT EXISTS construction_address_id UUID REFERENCES address(id),
    ADD COLUMN IF NOT EXISTS generating_address_id   UUID REFERENCES address(id);
