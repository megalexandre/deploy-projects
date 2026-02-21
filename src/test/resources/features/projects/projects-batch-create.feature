Feature: Project Batch Create

  Background:
    Given I am authenticated

  Scenario: Create multiple projects in batch with all fields

    When I POST the payload to "/projects/batch" with body:
    """
    {
        "projetos": [
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440001",
                "concessionaria": "CEMIG",
                "protocoloConcessionaria": "PROT-2024-001",
                "classe": "Residencial",
                "integrator": "Solar Tech Solutions",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Microgeração",
                "protecaoCC": "Disjuntor CC 20A",
                "potenciaSistema": 5.5
            },
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440002",
                "concessionaria": "CPFL",
                "protocoloConcessionaria": "PROT-2024-002",
                "classe": "Comercial",
                "integrator": "Solar Tech Solutions",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Minigeração",
                "protecaoCC": "Disjuntor CC 32A",
                "potenciaSistema": 15.8
            },
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440003",
                "concessionaria": "Enel",
                "protocoloConcessionaria": "PROT-2024-003",
                "classe": "Industrial",
                "integrator": "Green Energy Corp",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Minigeração",
                "protecaoCC": "Disjuntor CC 50A",
                "potenciaSistema": 75.0
            }
        ]
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "totalProcessado": 3,
      "sucessos": 3,
      "erros": 0,
      "projetosCriados": [
        {
          "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        },
        {
          "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        },
        {
          "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        }
      ]
    }
    """
    Then the table "projects" should have 3 records

  Scenario: Create multiple projects in batch with minimum required fields

    When I POST the payload to "/projects/batch" with body:
    """
    {
        "projetos": [
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440004",
                "concessionaria": "CEMIG",
                "protocoloConcessionaria": "PROT-2024-004",
                "classe": "Residencial",
                "integrator": "Solar Tech Solutions",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Microgeração"
            },
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440005",
                "concessionaria": "CPFL",
                "protocoloConcessionaria": "PROT-2024-005",
                "classe": "Comercial",
                "integrator": "Solar Tech Solutions",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Minigeração"
            }
        ]
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "totalProcessado": 2,
      "sucessos": 2,
      "erros": 0,
      "projetosCriados": [
        {
          "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        },
        {
          "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$"
        }
      ]
    }
    """
    Then the table "projects" should have 2 records

  Scenario: Try to create batch with empty list

    When I POST the payload to "/projects/batch" with body:
    """
    {
        "projetos": []
    }
    """
    Then the response status code should be 400

  Scenario: Try to create batch with invalid project data

    When I POST the payload to "/projects/batch" with body:
    """
    {
        "projetos": [
            {
                "clienteId": "invalid-uuid",
                "concessionaria": "CEMIG",
                "protocoloConcessionaria": "PROT-2024-006",
                "classe": "Residencial",
                "integrator": "Solar Tech Solutions",
                "modalidade": "Geração Distribuída",
                "enquadramento": "Microgeração"
            }
        ]
    }
    """
    Then the response status code should be 400

  Scenario: Try to create batch with missing required fields

    When I POST the payload to "/projects/batch" with body:
    """
    {
        "projetos": [
            {
                "clienteId": "550e8400-e29b-41d4-a716-446655440007",
                "concessionaria": "CEMIG",
                "classe": "Residencial"
            }
        ]
    }
    """
    Then the response status code should be 400
