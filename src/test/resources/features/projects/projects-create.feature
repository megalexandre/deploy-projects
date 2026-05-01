Feature: Project

  Background:
    Given I am authenticated

  Scenario: Create a project with all field

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

    When I POST the payload to "/projects" with body:
    """
    {
        "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "enderecoId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-2024-001",
        "classe": "Residencial",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "enquadramento": "Microgeração",
        "protecaoCC": "Disjuntor CC 20A",
        "potenciaSistema": 5.5,
        "status": "Em Análise",
        "valor": 15000.00,
        "unidade_controladora": "UC-001",
        "descrição": "Projeto de instalação de painel solar residencial",
        "servicos": ["Instalação", "Manutenção"],
        "tipo_projeto": "Solar",
        "projeto_fast_track": false,
        "coordenadas": {
            "latitude": -11.123456,
            "longitude": -40.123456
        }
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """
    Then the table "projects" should have 1 records

  Scenario: Create a project with minimum required fields

    Given the following customer exists in the database:
    """
    {
      "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
      "name": "Cliente Teste",
      "taxId": "12345678901",
      "phone": "71999999999",
      "email": "teste-create-min@email.com",
      "createdAt": "2026-02-10T10:00:00Z",
      "updatedAt": "2026-02-10T10:00:00Z"
    }
    """

    When I POST the payload to "/projects" with body:
    """
    {
        "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "clienteId": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-2024-001",
        "classe": "Residencial",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "enquadramento": "Microgeração",
        "status": "Em Análise",
        "valor": 15000.00,
        "unidade_controladora": "UC-001",
        "tipo_projeto": "Solar",
        "projeto_fast_track": false
    }
    """
    Then the response status code should be 201
    Then the table "projects" should have 1 records
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """
