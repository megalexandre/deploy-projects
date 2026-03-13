Feature: Customer

  Background:
    Given I am authenticated

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
