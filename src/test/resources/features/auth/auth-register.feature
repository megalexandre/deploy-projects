Feature: Authentication - Register User

  Scenario: Successfully register a new user

    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "alexandre",
      "email": "alexandre@mail.com",
      "profile": "analista",
      "password": "password"
    }
    """
    Then the response status code should be 201
    Then the response body should contain:
    """
    {
      "id": "${json-unit.regex}^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
      "name": "alexandre",
      "email": "alexandre@mail.com",
      "profile": "analista"
    }
    """
    Then the table "users" should have 1 records

  Scenario: Fail to register user with duplicate email
    Given a user exists with email "duplicate@mail.com" and password "password123"
    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "Another User",
      "email": "duplicate@mail.com",
      "profile": "developer",
      "password": "anotherpass"
    }
    """
    Then the response status code should be 409
    Then the response body should contain:
    """
    {
      "status": 409,
      "message": "Email already exists",
      "errors": {
        "email": "Email already exists"
      }
    }
    """

  Scenario: Fail to register user with invalid name (too short)
    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "a",
      "email": "test@mail.com",
      "profile": "user",
      "password": "password123"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to register user with invalid password (too short)
    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "Valid Name",
      "email": "test@mail.com",
      "profile": "user",
      "password": "123"
    }
    """
    Then the response status code should be 400

  Scenario: Fail to register user with missing required fields
    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "Valid Name"
    }
    """
    Then the response status code should be 400

  Scenario: Successfully register multiple users

    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "User One",
      "email": "user1@mail.com",
      "profile": "admin",
      "password": "password123"
    }
    """
    Then the response status code should be 201

    When I POST the payload to "/auth/register" with body:
    """
    {
      "name": "User Two",
      "email": "user2@mail.com",
      "profile": "developer",
      "password": "password456"
    }
    """
    Then the response status code should be 201
