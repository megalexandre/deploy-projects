Feature: Company

  Background:
    Given I am authenticated

  Scenario: Create a company with minus than minimum length name

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Ne"
    }
    """
    Then the response status code should be 400
    Then the table "concessionaires" should have 0 records


  Scenario: Create a company with more than maximum length name

    When I POST the payload to "/company" with body:
    """
    {
       "name": "Empresa de Geracao de Energia Renovavel e Distribuicao de Eletricidade Sustentavel do Brasil S.A. Unidade de Tratamento de Residuos Solidos e Gerenciamento de Recursos Hidricos do Nordeste Brasileiro LTDA Filial Numero Cento e Vinte e Oito Setor Administrativo X"
    }
    """
    Then the response status code should be 400
    Then the table "concessionaires" should have 0 records

  Scenario: Create a company without a name

    When I POST the payload to "/company" with body:
    """
    {}
    """
    Then the response status code should be 400
    Then the table "concessionaires" should have 0 records