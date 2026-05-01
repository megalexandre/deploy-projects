Feature: Customer

  Background:
    Given I am authenticated

  Scenario: avoid creation of customer with duplications

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

    Given the following customer exists in the database:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "name": "Cliente Teste",
      "taxId": "12345678901",
      "phone": "71999999999",
      "email": "teste-create-all@email.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
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
    Then the response status code should be 409
    Then the response body should contain:
    """
    {
      "status": 409,
      "message": "Tax ID already exists",
      "errors": {
          "cpfCnpj": "This tax ID is already registered"
      }
    }
    """
    Then the table "customers" should have 1 records