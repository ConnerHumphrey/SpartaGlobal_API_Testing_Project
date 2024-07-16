Feature: Logging into API

  Scenario: Logging in with valid account details
    Given I have access to a valid sparta global account
    When I send a post request to the /Auth/login endpoint with a JSON object containing the valid username and password.
    Then I will be sent back a response with a JSON object in the body containing an authorisation token
    And A status code of 200

  Scenario: Logging in with invalid account details
    Given I have access to a invalid sparta global account
    When I send a post request to the /Auth/login endpoint with a JSON object containing the invalid username and password.
    Then I will be sent back a response with a JSON object in the body containing a status code of 401 Unauthorised

  Scenario: Logging in with null usernames and passwords
    Given I do not have access to any sparta global accounts
    When I send a post request to the /Auth/login endpoint with a JSON object containing a null username and password.
    Then I will be sent back a response with a JSON object in the body containing a status code of 400
    And an informative error message stating i need a username and password

  Scenario: Logging in with a null password but valid username
    Given I only have access to a sparta global username
    When I send a post request to the /Auth/login endpoint with a JSON object containing a username and null password.
    Then I will be sent back a response with a JSON object in the body containing a status code of 400
    And an informative error message stating I need a password

  Scenario: Logging in with a null username but valid password
    Given I only have access to a sparta global password
    When I send a post request to the /Auth/login endpoint with a JSON object containing a null username and valid password.
    Then I will be sent back a response with a JSON object in the body containing a status code of 400
    And an informative error message stating I need a username