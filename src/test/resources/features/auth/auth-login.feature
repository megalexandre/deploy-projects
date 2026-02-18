Feature: Authentication - Login

  Scenario: Successful login with valid credentials

    Given a user exists with email "validuser@mail.com" and password "validpassword"
    When I POST the payload to "/auth/login" with body:
    """
    {
      "email": "validuser@mail.com",
      "password": "validpassword"
    }
    """
    Then the response status code should be 200
    Then the response body should contain:
    """
    {
      "token": "${json-unit.any-string}",
      "type": "Bearer",
      "expiresIn": 86400000
    }
    """

  Scenario: Fail to login with invalid email
    When I POST the payload to "/auth/login" with body:
    """
    {
      "email": "nonexistent@mail.com",
      "password": "anypassword"
    }
    """
    Then the response status code should be 401
    Then the response body should contain:
    """
    {
      "status": 401,
      "message": "Usuário inexistente ou senha inválida",
      "errors": {}
    }
    """

  Scenario: Fail to login with invalid password
    Given a user exists with email "testuser@mail.com" and password "correctpassword"
    When I POST the payload to "/auth/login" with body:
    """
    {
      "email": "testuser@mail.com",
      "password": "wrongpassword"
    }
    """
    Then the response status code should be 401
    Then the response body should contain:
    """
    {
      "status": 401,
      "message": "Usuário inexistente ou senha inválida",
      "errors": {}
    }
    """
