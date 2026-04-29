-- Create service entry items table (padrão de entrada)
CREATE TABLE IF NOT EXISTS service_entry_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_id UUID NOT NULL,
    connection_type VARCHAR(50) NOT NULL, -- 'Single-phase', 'Three-phase', etc.
    classification VARCHAR(50) NOT NULL, -- 'Residential', 'Commercial', 'Industrial'
    quantity INTEGER NOT NULL DEFAULT 1,
    circuit_breaker VARCHAR(20) NOT NULL, -- '63A', '100A', etc.
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_service_entry_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT chk_quantity_positive CHECK (quantity > 0)
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_service_entry_items_service_id ON service_entry_items(service_id);
CREATE INDEX IF NOT EXISTS idx_service_entry_items_connection_type ON service_entry_items(connection_type);
CREATE INDEX IF NOT EXISTS idx_service_entry_items_classification ON service_entry_items(classification);

-- Add comments
COMMENT ON TABLE service_entry_items IS 'Stores service entry standard items (padrão de entrada)';
COMMENT ON COLUMN service_entry_items.connection_type IS 'Type of electrical connection: Single-phase, Three-phase, etc.';
COMMENT ON COLUMN service_entry_items.classification IS 'Service classification: Residential, Commercial, Industrial';
COMMENT ON COLUMN service_entry_items.circuit_breaker IS 'Circuit breaker amperage (e.g., 63A, 100A)';

