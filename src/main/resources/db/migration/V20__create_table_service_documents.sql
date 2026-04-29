-- Create service documents table
CREATE TABLE IF NOT EXISTS service_documents (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    service_id UUID NOT NULL,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL, -- 'Driver License', 'Articles of Association', 'Energy Bill', etc.
    url_s3 VARCHAR(1000),
    size BIGINT NOT NULL,
    upload_date TIMESTAMPTZ NOT NULL DEFAULT now(),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_service_document_service FOREIGN KEY (service_id) REFERENCES services(id) ON DELETE CASCADE,
    CONSTRAINT chk_size_positive CHECK (size > 0)
);

-- Create indexes
CREATE INDEX IF NOT EXISTS idx_service_documents_service_id ON service_documents(service_id);
CREATE INDEX IF NOT EXISTS idx_service_documents_type ON service_documents(type);
CREATE INDEX IF NOT EXISTS idx_service_documents_upload_date ON service_documents(upload_date);

-- Add comments
COMMENT ON TABLE service_documents IS 'Stores documents associated with services';
COMMENT ON COLUMN service_documents.type IS 'Document type: Driver License, Articles of Association, Energy Bill, etc.';
COMMENT ON COLUMN service_documents.url_s3 IS 'S3 URL where the document is stored';
COMMENT ON COLUMN service_documents.size IS 'File size in bytes';

