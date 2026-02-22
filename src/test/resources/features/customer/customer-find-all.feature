Feature: Customer Find All

  Background:
    Given I am authenticated

  Scenario: Get all customers when there are no customers

    When I GET "/customers"
    Then the response status code should be 200
    Then the response body should contain:
    """
    []
    """

  Scenario: Get all customers when there is one customer

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

    When I GET "/customers"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
      }
    ]
    """

  Scenario: Get all customers when there are multiple customers

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
    Given the following customer exists in the database:
    """
    {
      "id": "550e8400-e29b-41d4-a716-446655440002",
      "name": "Maria Santos",
      "taxId": "98765432100",
      "phone": "11912345678",
      "email": "maria.santos@example.com",
      "createdAt": "2026-02-10T11:00:00Z",
      "updatedAt": "2026-02-10T11:00:00Z"
    }
    """

    When I GET "/customers"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "550e8400-e29b-41d4-a716-446655440001",
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
      },
      {
        "id": "550e8400-e29b-41d4-a716-446655440002",
        "nome": "Maria Santos",
        "cpfCnpj": "98765432100",
        "telefone": "11912345678",
        "email": "maria.santos@example.com"
      }
    ]
    """
