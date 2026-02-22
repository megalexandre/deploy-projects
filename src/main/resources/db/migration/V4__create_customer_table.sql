CREATE TABLE IF NOT EXISTS customers (
     id UUID PRIMARY KEY,
     name TEXT NOT NULL,
     email TEXT NOT NULL UNIQUE,
     tax_id TEXT NOT NULL UNIQUE,
     phone TEXT NOT NULL,
     created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
     updated_at TIMESTAMPTZ NOT NULL DEFAULT now()
);

