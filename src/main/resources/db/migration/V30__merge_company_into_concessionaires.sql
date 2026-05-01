ALTER TABLE concessionaires ADD COLUMN IF NOT EXISTS acronym VARCHAR;

INSERT INTO concessionaires (id, name, acronym)
SELECT id, name, acronym FROM company
ON CONFLICT (name) DO UPDATE SET acronym = EXCLUDED.acronym;

DROP TABLE company;
