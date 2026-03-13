Feature: Customer Update

  Background:
    Given I am authenticated

  Scenario: Update an existing customer with all fields

    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I PUT the payload to "/customers" with body:
    """
    {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nome": "João da Silva Junior",
        "cpfCnpj": "12345678901",
        "telefone": "11999999999",
        "email": "joao.junior@example.com"
    }
    """
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "addressId":null,
      "email":"joao.junior@example.com",
      "name":"João da Silva Junior",
      "phone":"11999999999",
      "taxId":"12345678901"
    }
    """

  Scenario: Update a customer with CNPJ

    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "name": "Empresa Solar LTDA",
      "taxId": "12345678000190",
      "phone": "1133334444",
      "email": "contato@empresasolar.com.br",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I PUT the payload to "/customers" with body:
    """
    {
        "id": "550e8400-e29b-41d4-a716-446655440002",
        "nome": "Empresa Solar LTDA ME",
        "cpfCnpj": "12345678000190",
        "telefone": "1144445555",
        "email": "comercial@empresasolar.com.br"
    }
    """
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "addressId":null,
      "email":"comercial@empresasolar.com.br",
      "name":"Empresa Solar LTDA ME",
      "phone":"1144445555",
      "taxId":"12345678000190"
    }
    """

  Scenario: Update customer fails with invalid email

    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440001",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I PUT the payload to "/customers" with body:
    """
    {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "invalid-email"
    }
    """
    Then the response status code should be 400
