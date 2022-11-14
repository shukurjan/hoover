Feature: Hoover tests

  Background:
  Given The baseURL

#basic positive case:
  @hoover
  Scenario: Hoover basic positive cleaning test
    Given The details given in RequestPayload.json document
    When I send a POST request to cleaning-sessions resource
    Then The response payload should be correct in accordance with the request







  @hoover
    Scenario: Test the requirement that the room size should be square
      Given The following room size details
        | 2 |
        | 6 |
      When I send a POST request to cleaning-sessions resource
      Then The response status code should not be 200


    @hoover
      Scenario: Test the tolerance of non-allowed characters in the direction
        Given The details given in RequestPayload.json document and given the following direction word "ABCDEFG"
        When I send a POST request to cleaning-sessions resource
        Then The response status code should not be 200


    @hoover
      Scenario: Test for negative room size
        Given The details given in RequestPayload.json document and negative roomSize coordinates
        |-6|
        |-6|
        When  I send a POST request to cleaning-sessions resource
        Then The response status code should be 400



        @hoover
      Scenario: Test the correctness of patches cleaned by sending request with no dirty patches
        Given That I send a request with no dirty patches
        When  I send a POST request to cleaning-sessions resource
        Then  The patches cleaned should show 0
#somehow patches cleaned returns at least one at all times






  @hoover
      Scenario Outline: Test for the behaviour of the Hoover when given instructions to go outside the walls of the room
        Given The roomsize 5 by 5, Hoover starting point of 4 and 4, and directions "<direction>"
        When I send a POST request to cleaning-sessions resource
        Then I should see that the Hoover doesn't end up beyond the limits of the room
      Examples:
        | direction               |
        | NNNNNNNNNNNNNNNNNNNNNNN |
        | EEEEEEEEEEEEEEEEEEEEEEE |
        | SSSSSSSSSSSSSSSSSSSSSSS |
        | WWWWWWWWWWWWWWWWWWWWWWW |



#Why doesn't this reach the wall ??  It stays one over on the North side and East side?  Off by one error?
#  @hoover
    Scenario: Test for the behaviour of the Hoover when given instructions to go outside Northernmost wall limits
      Given The roomsize 5 by 5, Hoover starting point of 4 and 4, and directions "NNNNNNNNNNNNNNNNNNNNNNNNNN"
      When I send a POST request to cleaning-sessions resource
      Then I should see that the Hoover ends up at the correct last Northern positions


  @hoover
  Scenario: Test for the behaviour of the Hoover when given instructions to go outside Easternmost wall limits
    Given The roomsize 5 by 5, Hoover starting point of 0 and 0, and directions "EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE"
    When I send a POST request to cleaning-sessions resource
    Then I should see that the Hoover ends up at the correct last Eastern positions



#  Scenario: Test roomSize by sending more than two coordinates - still returns correct coords based on first 2 nums provided
#  not sure if this is by design, or if this test case should fail - might need more info
  @hoover
  Scenario: Test roomSize by providing more than 2 coordinates
    Given The following room size details2
      | 2  |
      | 6  |
      | 9  |
      | 55 |
    When I send a POST request to cleaning-sessions resource
    Then The response status code should be 400






















































#Should we be able to navigate the Hoover if it is originally outside the walls?
# Are hoover coords required in Json request payload?
#  test with negative room size - returns coordinates [0,0] and some patches are still cleaned  - bug?









































