-- Add new fields to projects table
ALTER TABLE projects
ADD COLUMN unit_control VARCHAR(250) NOT NULL DEFAULT 'default',
ADD COLUMN description VARCHAR(1024),
ADD COLUMN services_names TEXT,
ADD COLUMN project_type VARCHAR(250) NOT NULL DEFAULT 'standard',
ADD COLUMN fast_track BOOLEAN NOT NULL DEFAULT false;

-- Remove default values after adding columns
ALTER TABLE projects
ALTER COLUMN unit_control DROP DEFAULT,
ALTER COLUMN project_type DROP DEFAULT,
ALTER COLUMN fast_track DROP DEFAULT;

