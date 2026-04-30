Feature: Services Create

  Background:
    Given I am authenticated

  Scenario: Create a service with all fields

    Given the following customer exists in the database:
    """
    {
      "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao@example.com",
      "createdAt": "2026-01-01T00:00:00Z",
      "updatedAt": "2026-01-01T00:00:00Z"
    }
    """
    Given the following concessionaire exists in the database:
    """
    {
      "id": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "name": "CEMIG",
      "code": "CEMIG",
      "region": "MG"
    }
    """

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800,
      "discountCouponPercentage": 10,
      "observations": "Complete service with all fields filled.",
      "supplyVoltage": "127/220V",
      "coordinates": {
        "latitude": "-23.550520",
        "longitude": "-46.633308"
      },
      "generatingConsumerUnit": "1234567890",
      "poleDistanceOver30m": false,
      "constructionAddress": {
        "zipCode": "01310100",
        "street": "Avenida Paulista",
        "number": "1000",
        "complement": "Room 12",
        "neighborhood": "Bela Vista",
        "city": "Sao Paulo",
        "state": "SP",
        "link": "https://maps.google.com/?q=Avenida+Paulista,+1000"
      },
      "generatingAddress": {
        "zipCode": "01311000",
        "street": "Rua da Geradora",
        "number": "200",
        "neighborhood": "Centro",
        "city": "Sao Paulo",
        "state": "SP"
      },
      "serviceEntryItems": [
        {
          "connectionType": "Single-phase",
          "classification": "Residential",
          "quantity": 1,
          "circuitBreaker": "63A"
        }
      ],
      "apportionments": [
        {
          "consumerUnit": "99887766",
          "address": "Rua Beneficiaria 1, 100",
          "class": "Residential",
          "percentage": 100
        }
      ]
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """
    Then the table "services" should have 1 records
    Then the table "address" should have 2 records
    Then the table "service_entry_items" should have 1 records
    Then the table "apportionments" should have 1 records

  Scenario: Create a service with minimum required fields

    Given the following customer exists in the database:
    """
    {
      "id": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "name": "João da Silva",
      "taxId": "12345678901",
      "phone": "11987654321",
      "email": "joao@example.com",
      "createdAt": "2026-01-01T00:00:00Z",
      "updatedAt": "2026-01-01T00:00:00Z"
    }
    """
    Given the following concessionaire exists in the database:
    """
    {
      "id": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "name": "CEMIG",
      "code": "CEMIG",
      "region": "MG"
    }
    """

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """
    Then the table "services" should have 1 records
    Then the table "address" should have 0 records

  Scenario: Fail to create a service without type

    When I POST the payload to "/services" with body:
    """
    {
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a service without customerId

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a service with invalid customerId UUID

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "customerId": "not-a-valid-uuid",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a service with negative amount

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": -100
    }
    """
    Then the response status code should be 400

  Scenario: Fail to create a service with discount percentage out of range

    When I POST the payload to "/services" with body:
    """
    {
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800,
      "discountCouponPercentage": 150
    }
    """
    Then the response status code should be 400
