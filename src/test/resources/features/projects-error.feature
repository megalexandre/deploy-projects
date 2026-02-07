Feature: Project

  Scenario: try to create a project without name

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
          "name": "O nome do projeto é obrigatório."
      }
    }
    """

  Scenario: try to create a project with name more than 255 characters

    When I POST the payload to "/projects" with body:
    """
    {
        "name": "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
    }
    """
    Then the response status code should be 400
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
          "name": "O nome do projeto deve ter entre 2 e 250 caracteres."
      }
    }
    """

  Scenario: try to create a project with name minus than 2 characters

    When I POST the payload to "/projects" with body:
    """
    {
        "name":"A"
    }
    """
    Then the response status code should be 400
    Then the response body should contain:
    """
    {
      "status": 400,
      "message": "Validation Failed",
      "errors": {
          "name": "O nome do projeto deve ter entre 2 e 250 caracteres."
      }
    }
    """