Feature: Customer

  Background:
    Given I am authenticated

  Scenario: Create a customer with all fields

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com"
    }
    """
    Then the table "customers" should have 1 records

  Scenario: Create a customer with CNPJ

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "Empresa Solar LTDA",
        "cpfCnpj": "12345678000190",
        "telefone": "1133334444",
        "email": "contato@empresasolar.com.br"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "Empresa Solar LTDA",
      "taxId": "12345678000190",
      "phone": "1133334444",
      "email": "contato@empresasolar.com.br"
    }
    """
    Then the table "customers" should have 1 records

  Scenario: Fail to create a customer without name

    When I POST the payload to "/customers" with body:
    """
    {
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer without CPF/CNPJ

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer with invalid CPF/CNPJ

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "cpfCnpj": "123",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer without phone

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer without email

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer with invalid email

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "invalid-email"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a customer with name too short

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "J",
        "cpfCnpj": "12345678901",
        "telefone": "11987654321",
        "email": "joao.silva@example.com"
    }
    """
    Then the response status code should be 400
