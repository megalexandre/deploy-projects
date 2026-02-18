Feature: Project

  Scenario: try to create a project without required fields

    When I POST the payload to "/projects" with body:
    """
    {
    }
    """
    Then the response status code should be 400
    Then the table "projects" should have 0 records
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
        "clientId": "O cliente é obrigatório.",
        "utilityCompany": "A concessionária é obrigatória.",
        "utilityProtocol": "O protocolo da concessionária é obrigatório.",
        "customerClass": "A classe do cliente é obrigatória.",
        "integrator": "O integrador é obrigatório.",
        "modality": "A modalidade é obrigatória.",
        "framework": "O enquadramento é obrigatório."
      }
    }
    """

  Scenario: try to create a project with invalid clientId

    When I POST the payload to "/projects" with body:
    """
    {
        "clienteId": "1231",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-001",
        "classe": "Residencial",
        "integrator": "Solar Tech",
        "modalidade": "GD",
        "enquadramento": "Micro"
    }
    """
    Then the response status code should be 400
    Then the table "projects" should have 0 records
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
          "clientId": "O UUID informado é inválido."
      }
    }
    """