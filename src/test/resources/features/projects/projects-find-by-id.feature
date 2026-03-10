Feature: Project

  Background:
    Given I am authenticated

  Scenario: Get by id a project that exists

    Given the following project exists in the database:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "clientId": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "utilityCompany": "CEMIG",
      "utilityProtocol": "PROT-2024-001",
      "customerClass": "Residencial",
      "integrator": "Solar Tech Solutions",
      "modality": "Geração Distribuída",
      "framework": "Microgeração",
      "dcProtection": "Disjuntor CC 20A",
      "systemPower": 5.5,
      "status": "Em Análise",
      "amount": 15000.00,
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I GET "/projects/da30fcdc-b9ed-402b-a359-de324226bee7"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "classe": "Residencial",
      "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "endereco": null,
      "concessionaria": "CEMIG",
      "createdAt": "2026-02-10T10:00:00Z",
      "enquadramento": "Microgeração",
      "integrator": "Solar Tech Solutions",
      "modalidade": "Geração Distribuída",
      "potenciaSistema": 5.5,
      "protecaoCC": "Disjuntor CC 20A",
      "protocoloConcessionaria": "PROT-2024-001",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """


  Scenario: Get by id a project with address:

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

    Given the following project exists in the database:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "addressId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "clientId": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "utilityCompany": "CEMIG",
      "utilityProtocol": "PROT-2024-001",
      "customerClass": "Residencial",
      "integrator": "Solar Tech Solutions",
      "modality": "Geração Distribuída",
      "framework": "Microgeração",
      "dcProtection": "Disjuntor CC 20A",
      "systemPower": 5.5,
      "status": "Em Análise",
      "amount": 15000.00,
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I GET "/projects/da30fcdc-b9ed-402b-a359-de324226bee7"
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "classe": "Residencial",
      "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "endereco":
      {
        "id":"019ca71b-3183-7a8b-8f71-e44a327a7846",
        "link":"https://maps.google.com/?q=-11.123456,-40.123456",
        "place":"any name",
        "cep":"41234567",
        "number":"12",
        "address":"any name",
        "complement":"description",
        "neighborhood":"jacobina III",
        "city":"jacobina",
        "state":"ba"
      },
      "concessionaria": "CEMIG",
      "createdAt": "2026-02-10T10:00:00Z",
      "enquadramento": "Microgeração",
      "integrator": "Solar Tech Solutions",
      "modalidade": "Geração Distribuída",
      "potenciaSistema": 5.5,
      "protecaoCC": "Disjuntor CC 20A",
      "protocoloConcessionaria": "PROT-2024-001",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """


  Scenario: Get by id a project that not exists

    When I GET "/projects/da30fcdc-b9ed-402b-a359-de324226bee7"
    Then the response status code should be 404

