ALTER TABLE projects
    ALTER COLUMN services_names TYPE TEXT[]
    USING CASE
        WHEN services_names IS NULL OR services_names = '' THEN NULL
        ELSE string_to_array(services_names, ',')
    END;
