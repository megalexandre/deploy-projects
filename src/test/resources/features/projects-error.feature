Feature: Project

  Scenario: try to create a project without required fields

    When I POST the payload to "/projects" with body:
    """
    {
    }
    """
    Then the response status code should be 400
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

  Scenario: try to create a project with clientId more than 250 characters

    When I POST the payload to "/projects" with body:
    """
    {
        "clienteId": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-001",
        "classe": "Residencial",
        "integrator": "Solar Tech",
        "modalidade": "GD",
        "enquadramento": "Micro"
    }
    """
    Then the response status code should be 400
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
          "clientId": "O cliente deve ter entre 2 e 250 caracteres."
      }
    }
    """

  Scenario: try to create a project with clientId less than 2 characters

    When I POST the payload to "/projects" with body:
    """
    {
        "clienteId": "A",
        "concessionaria": "CEMIG",
        "protocoloConcessionaria": "PROT-001",
        "classe": "Residencial",
        "integrator": "Solar Tech",
        "modalidade": "GD",
        "enquadramento": "Micro"
    }
    """
    Then the response status code should be 400
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
          "clientId": "O cliente deve ter entre 2 e 250 caracteres."
      }
    }
    """