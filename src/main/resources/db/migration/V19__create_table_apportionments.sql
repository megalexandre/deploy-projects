-- Create apportionments table (rateios)
CREATE TABLE IF NOT EXISTS apportionments (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_id UUID NOT NULL,
    consumer_unit VARCHAR(100) NOT NULL,
    address VARCHAR(255) NOT NULL,
    class VARCHAR(50) NOT NULL, -- 'Residential', 'Commercial', 'Industrial'
    percentage INTEGER NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_apportionment_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT chk_percentage_range CHECK (percentage >= 0 AND percentage <= 100)
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_apportionments_service_id ON apportionments(service_id);
CREATE INDEX IF NOT EXISTS idx_apportionments_consumer_unit ON apportionments(consumer_unit);
CREATE INDEX IF NOT EXISTS idx_apportionments_class ON apportionments(class);

-- Add comments
COMMENT ON TABLE apportionments IS 'Stores energy apportionments (rateios) for services';
COMMENT ON COLUMN apportionments.consumer_unit IS 'Consumer unit code (UC - Unidade Consumidora)';
COMMENT ON COLUMN apportionments.class IS 'Consumer class: Residential, Commercial, Industrial';
COMMENT ON COLUMN apportionments.percentage IS 'Percentage of apportionment (0-100)';

