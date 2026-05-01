ALTER TABLE projects
    ADD CONSTRAINT fk_projects_customer FOREIGN KEY (client_id) REFERENCES customers(id);

ALTER TABLE projects
    ADD CONSTRAINT fk_projects_address FOREIGN KEY (address_id) REFERENCES address(id);

ALTER TABLE customers
    ADD CONSTRAINT fk_customers_address FOREIGN KEY (address_id) REFERENCES address(id);
