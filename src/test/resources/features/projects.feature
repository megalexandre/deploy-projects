Feature: Project

  Scenario: Create a project

    When I POST the payload to "/projects" with body:
    """
    {
      "name": "My Project"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "My Project"
    }
    """
