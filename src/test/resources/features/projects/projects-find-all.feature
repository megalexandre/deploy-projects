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

    Given the following customer exists in the database:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "name": "Cliente Teste",
      "taxId": "12345678901",
      "phone": "71999999999",
      "email": "teste-findall@email.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

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
      "amount": 15000,
      "unitControl": "UC-001",
      "description": "Teste find all",
      "servicesNames": ["Instalação"],
      "projectType": "Solar",
      "fastTrack": false,
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
        "classe": "Residencial",
        "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "concessionaria": "CEMIG",
        "descrição": "Teste find all",
        "enquadramento": "Microgeração",
        "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "potenciaSistema": 5.5,
        "projeto_fast_track": false,
        "protecaoCC": "Disjuntor CC 20A",
        "protocoloConcessionaria": "PROT-2024-001",
        "servicos": ["Instalação"],
        "status": "Em Análise",
        "tipo_projeto": "Solar",
        "unidade_controladora": "UC-001",
        "valor": 15000.00
      }
    ]
    """
