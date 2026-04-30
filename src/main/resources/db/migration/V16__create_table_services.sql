-- Create services table
CREATE TABLE IF NOT EXISTS services (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(100) NOT NULL,
    customer_id UUID NOT NULL,
    concessionaire_id UUID NOT NULL,
    opening_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    discount_coupon_percentage INTEGER,
    observations TEXT,
    supply_voltage VARCHAR(50),
    latitude VARCHAR(50),
    longitude VARCHAR(50),
    pole_distance_over_30m BOOLEAN DEFAULT FALSE,
    generating_consumer_unit VARCHAR(100),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_service_customer FOREIGN KEY (customer_id) REFERENCES customers(id) ON DELETE CASCADE,
    CONSTRAINT chk_amount_positive CHECK (amount >= 0),
    CONSTRAINT chk_discount_percentage CHECK (discount_coupon_percentage >= 0 AND discount_coupon_percentage <= 100)
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_services_customer_id ON services(customer_id);
CREATE INDEX IF NOT EXISTS idx_services_concessionaire_id ON services(concessionaire_id);
CREATE INDEX IF NOT EXISTS idx_services_type ON services(type);
CREATE INDEX IF NOT EXISTS idx_services_opening_date ON services(opening_date);

-- Add comments for documentation
COMMENT ON TABLE services IS 'Stores service requests for new connections, upgrades, and other utility services';
COMMENT ON COLUMN services.type IS 'Type of service: new_connection, upgrade, etc.';
COMMENT ON COLUMN services.pole_distance_over_30m IS 'Indicates if the pole distance is over 30 meters';
COMMENT ON COLUMN services.discount_coupon_percentage IS 'Discount percentage applied (0-100)';

