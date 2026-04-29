-- Add foreign key constraint to services table for concessionaire
ALTER TABLE services
ADD CONSTRAINT fk_service_concessionaire
FOREIGN KEY (concessionaire_id)
REFERENCES concessionaires(id)
ON DELETE RESTRICT;

-- Add comment
COMMENT ON CONSTRAINT fk_service_concessionaire ON services IS 'References the utility company (concessionaire) for the service';

