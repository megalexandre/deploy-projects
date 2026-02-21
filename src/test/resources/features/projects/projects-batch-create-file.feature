Feature: Project Batch Create with XLSX file

  Background:
    Given I am authenticated

  Scenario: Create multiple projects in batch uploading XLSX file with all fields

    When I POST to "/projects/batch" with file "projects-batch.xlsx" with content:
    """
    clienteId,concessionaria,protocoloConcessionaria,classe,integrator,modalidade,enquadramento,protecaoCC,potenciaSistema
    550e8400-e29b-41d4-a716-446655440001,CEMIG,PROT-2024-001,Residencial,Solar Tech Solutions,Geração Distribuída,Microgeração,Disjuntor CC 20A,5.5
    550e8400-e29b-41d4-a716-446655440002,CPFL,PROT-2024-002,Comercial,Solar Tech Solutions,Geração Distribuída,Minigeração,Disjuntor CC 32A,15.8
    550e8400-e29b-41d4-a716-446655440003,Enel,PROT-2024-003,Industrial,Green Energy Corp,Geração Distribuída,Minigeração,Disjuntor CC 50A,75.0
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

  Scenario: Create multiple projects in batch uploading CSV file

    When I POST to "/projects/batch" with file "projects-batch.csv" with content:
    """
    clienteId,concessionaria,protocoloConcessionaria,classe,integrator,modalidade,enquadramento,protecaoCC,potenciaSistema
    550e8400-e29b-41d4-a716-446655440001,CEMIG,PROT-2024-001,Residencial,Solar Tech Solutions,Geração Distribuída,Microgeração,Disjuntor CC 20A,5.5
    550e8400-e29b-41d4-a716-446655440002,CPFL,PROT-2024-002,Comercial,Solar Tech Solutions,Geração Distribuída,Minigeração,Disjuntor CC 32A,15.8
    550e8400-e29b-41d4-a716-446655440003,Enel,PROT-2024-003,Industrial,Green Energy Corp,Geração Distribuída,Minigeração,Disjuntor CC 50A,75.0
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

  Scenario: Create multiple projects with XLS file (older format)

    When I POST to "/projects/batch" with file "projects-batch.xls" with content:
    """
    clienteId,concessionaria,protocoloConcessionaria,classe,integrator,modalidade,enquadramento,protecaoCC,potenciaSistema
    550e8400-e29b-41d4-a716-446655440001,CEMIG,PROT-2024-001,Residencial,Solar Tech Solutions,Geração Distribuída,Microgeração,Disjuntor CC 20A,5.5
    550e8400-e29b-41d4-a716-446655440002,CPFL,PROT-2024-002,Comercial,Solar Tech Solutions,Geração Distribuída,Minigeração,Disjuntor CC 32A,15.8
    550e8400-e29b-41d4-a716-446655440003,Enel,PROT-2024-003,Industrial,Green Energy Corp,Geração Distribuída,Minigeração,Disjuntor CC 50A,75.0
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

  Scenario: Create projects with minimum required fields

    When I POST to "/projects/batch" with file "projects-batch.xlsx" with content:
    """
    clienteId,concessionaria,protocoloConcessionaria,classe,integrator,modalidade,enquadramento
    550e8400-e29b-41d4-a716-446655440004,CEMIG,PROT-2024-004,Residencial,Solar Tech Solutions,Geração Distribuída,Microgeração
    550e8400-e29b-41d4-a716-446655440005,CPFL,PROT-2024-005,Comercial,Solar Tech Solutions,Geração Distribuída,Minigeração
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


