UPDATE address SET place = '' WHERE place IS NULL;
ALTER TABLE address ALTER COLUMN place SET NOT NULL;
