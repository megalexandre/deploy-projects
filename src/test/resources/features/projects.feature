Feature: Project

  Scenario: Create a project with all field

    When I POST the payload to "/projects" with body:
    """
    {
        "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "clienteId": "312",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-2024-001",
        "classe": "Residencial",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "enquadramento": "Microgeração",
        "protecaoCC": "Disjuntor CC 20A",
        "potenciaSistema": 5.5
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """

  Scenario: Create a project with minimum required fields

    When I POST the payload to "/projects" with body:
    """
    {
        "id": "da30fcdc-b9ed-402b-a359-de324226bee7",
        "clienteId": "312",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-2024-001",
        "classe": "Residencial",
        "integrator": "Solar Tech Solutions",
        "modalidade": "Geração Distribuída",
        "enquadramento": "Microgeração"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
    }
    """