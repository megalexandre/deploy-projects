Feature: Customer

  Background:
    Given I am authenticated

  Scenario: Create a customer with all fields

    Given the following address exists in the database:
    """
    {
       "id": "019ca71b-3183-7a8b-8f71-e44a327a7846",
       "cep": "41234567",
       "place": "any name",
       "number": "12",
       "address": "any name",
       "complement": "description",
       "neighborhood": "jacobina III",
       "city": "jacobina",
       "state": "ba",
       "link": "https://maps.google.com/?q=-11.123456,-40.123456"
    }
    """

    When I POST the payload to "/customers" with body:
    """
    {
        "nome": "João da Silva",
        "addressId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
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
      "addressId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao.silva@example.com"
    }
    """
    Then the table "customers" should have 1 records

  Scenario: Create a customer with minimal fields

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
      "addressId": null,
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
      "addressId": null,
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

