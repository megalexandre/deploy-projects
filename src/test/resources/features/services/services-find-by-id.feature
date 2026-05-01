Feature: Services Find By Id

  Background:
    Given I am authenticated

  Scenario: Get a service by id with all fields

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
    Given the following service exists in the database:
    """
    {
      "id": "c3d4e5f6-a7b8-9012-cdef-123456789012",
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800.00,
      "discountCouponPercentage": 10,
      "observations": "Complete service test",
      "supplyVoltage": "127/220V",
      "coordinates": {
        "latitude": -23.55052,
        "longitude": -46.633308
      },
      "generatingConsumerUnit": "1234567890",
      "poleDistanceOver30m": false,
      "constructionAddress": {
        "id": "d4e5f6a7-b8c9-0123-defa-234567890123",
        "place": "Avenida Paulista",
        "cep": "01310100",
        "address": "Avenida Paulista",
        "number": "1000",
        "complement": "Room 12",
        "neighborhood": "Bela Vista",
        "city": "Sao Paulo",
        "state": "SP",
        "link": "https://maps.google.com/?q=Avenida+Paulista,+1000"
      },
      "generatingAddress": {
        "id": "e5f6a7b8-c9d0-1234-efab-345678901234",
        "place": "Rua da Geradora",
        "cep": "01311000",
        "address": "Rua da Geradora",
        "number": "200",
        "neighborhood": "Centro",
        "city": "Sao Paulo",
        "state": "SP",
        "link": null
      },
      "serviceEntryItems": [
        {
          "id": "f6a7b8c9-d0e1-2345-fabc-456789012345",
          "connectionType": "Single-phase",
          "classification": "Residential",
          "quantity": 1,
          "circuitBreaker": "63A"
        }
      ],
      "apportionments": [
        {
          "id": "a7b8c9d0-e1f2-3456-abcd-567890123456",
          "consumerUnit": "99887766",
          "address": "Rua Beneficiaria 1, 100",
          "classification": "Residential",
          "percentage": 100
        }
      ],
      "createdAt": "2026-03-01T10:00:00Z",
      "updatedAt": "2026-03-01T10:00:00Z"
    }
    """

    When I GET "/services/c3d4e5f6-a7b8-9012-cdef-123456789012"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "c3d4e5f6-a7b8-9012-cdef-123456789012",
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800.00,
      "discountCouponPercentage": 10,
      "observations": "Complete service test",
      "supplyVoltage": "127/220V",
      "coordinates": {
        "latitude": -23.55052,
        "longitude": -46.633308
      },
      "generatingConsumerUnit": "1234567890",
      "poleDistanceOver30m": false,
      "constructionAddress": {
        "id": "d4e5f6a7-b8c9-0123-defa-234567890123",
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
        "id": "e5f6a7b8-c9d0-1234-efab-345678901234",
        "zipCode": "01311000",
        "street": "Rua da Geradora",
        "number": "200",
        "complement": null,
        "neighborhood": "Centro",
        "city": "Sao Paulo",
        "state": "SP",
        "link": null
      },
      "serviceEntryItems": [
        {
          "id": "f6a7b8c9-d0e1-2345-fabc-456789012345",
          "connectionType": "Single-phase",
          "classification": "Residential",
          "quantity": 1,
          "circuitBreaker": "63A"
        }
      ],
      "apportionments": [
        {
          "id": "a7b8c9d0-e1f2-3456-abcd-567890123456",
          "consumerUnit": "99887766",
          "address": "Rua Beneficiaria 1, 100",
          "classification": "Residential",
          "percentage": 100
        }
      ]
    }
    """

  Scenario: Get a service by id without optional fields

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
    Given the following service exists in the database:
    """
    {
      "id": "c3d4e5f6-a7b8-9012-cdef-123456789012",
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800.00,
      "poleDistanceOver30m": false,
      "serviceEntryItems": [],
      "apportionments": [],
      "createdAt": "2026-03-01T10:00:00Z",
      "updatedAt": "2026-03-01T10:00:00Z"
    }
    """

    When I GET "/services/c3d4e5f6-a7b8-9012-cdef-123456789012"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "c3d4e5f6-a7b8-9012-cdef-123456789012",
      "type": "new_connection",
      "customerId": "a1b2c3d4-e5f6-7890-abcd-ef1234567890",
      "concessionaireId": "b2c3d4e5-f6a7-8901-bcde-f12345678901",
      "openingDate": "2026-03-23",
      "amount": 1800.00,
      "discountCouponPercentage": null,
      "observations": null,
      "supplyVoltage": null,
      "coordinates": null,
      "generatingConsumerUnit": null,
      "poleDistanceOver30m": false,
      "constructionAddress": null,
      "generatingAddress": null,
      "serviceEntryItems": [],
      "apportionments": []
    }
    """

  Scenario: Get a service by id that does not exist

    When I GET "/services/c3d4e5f6-a7b8-9012-cdef-123456789012"
    Then the response status code should be 404
