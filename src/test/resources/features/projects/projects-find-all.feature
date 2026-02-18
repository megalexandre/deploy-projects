Feature: Project

  Background:
    Given I am authenticated

  Scenario: Get all projects when there are no projects

    When I GET "/projects"
    Then the response status code should be 200
    Then the response body should contain:
    """
    []
    """






































































































































































  Scenario: Get all projects when there are one project

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
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I GET "/projects"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-2024-001",
        "classe": "Residencial",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "enquadramento": "Microgeração",
        "protecaoCC": "Disjuntor CC 20A",
        "potenciaSistema": 5.5
      }
    ]
    """
