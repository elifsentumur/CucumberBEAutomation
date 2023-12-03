Feature: IMDb ID Search

  Scenario: Search for "Harry Potter" and Verify Title True
    Given the API is up and running
    When a GET request is sent to "Harry Potter"
    Then the search response status code should be 200
    Then the IMDb ID for "Harry Potter and the Sorcerer's Stone" should be retrieved
    And id value should be checked


  Scenario: Question 2 True
    Given the API is up and running
    When a GET request is sent to "Harry Potter"
    Then the search response status code should be 200
    Then the IMDb ID for "Harry Potter and the Sorcerer's Stone" should be retrieved
    And id value should be checked
    When a GET request is sent to the "type" search endpoint with type "movie" and user specified type
    Then the search response status code should be 200
    And the title of the movie should be "Harry Potter and the Sorcerer's Stone"


  Scenario: Verify consistency between By Search and By ID or Title methods
    Given the API is up and running
    When a GET request is sent to "Harry Potter"
    Then the search response status code should be 200
    Then the IMDb ID for "Harry Potter and the Sorcerer's Stone" should be retrieved
    And id value should be checked
    When a GET request is sent to the "t" search endpoint with type "Harry Potter and the Sorcerer's Stone" and user specified type
    Then the search response status code should be 200
    And the title of the movie should be "Harry Potter and the Sorcerer's Stone"
    When a GET request is sent to and used with imbdID
    Then this request should be title "Harry Potter and the Sorcerer's Stone"
    And get search id value should be checked
    And checking the header value of its two transactions
