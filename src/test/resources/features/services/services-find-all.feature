Feature: Services Find All

  Background:
    Given I am authenticated

  Scenario: Get all services when there are none

    When I GET "/services"
    Then the response status code should be 200
    Then the response body should contain:
    """
    []
    """

  Scenario: Get all services when there is one

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

    When I GET "/services"
    Then the response status code should be 200
    Then the response body should have 1 items
    Then the response body should contain:
    """
    [
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
    ]
    """
