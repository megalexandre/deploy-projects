-- Create service addresses table for construction and generating addresses
CREATE TABLE IF NOT EXISTS service_addresses (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_id UUID NOT NULL,
    address_type VARCHAR(50) NOT NULL, -- 'construction' or 'generating'
    zip_code VARCHAR(20) NOT NULL,
    street VARCHAR(255) NOT NULL,
    number VARCHAR(20) NOT NULL,
    complement VARCHAR(255),
    neighborhood VARCHAR(100) NOT NULL,
    city VARCHAR(100) NOT NULL,
    state VARCHAR(2) NOT NULL,
    link TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_service_address_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT chk_address_type CHECK (address_type IN ('construction', 'generating'))
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_service_addresses_service_id ON service_addresses(service_id);
CREATE INDEX IF NOT EXISTS idx_service_addresses_type ON service_addresses(address_type);
CREATE INDEX IF NOT EXISTS idx_service_addresses_zip_code ON service_addresses(zip_code);

-- Add comments
COMMENT ON TABLE service_addresses IS 'Stores construction and generating addresses for services';
COMMENT ON COLUMN service_addresses.address_type IS 'Type of address: construction or generating';

