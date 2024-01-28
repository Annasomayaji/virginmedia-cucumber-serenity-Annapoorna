Feature: Validating Jackpot feature

  @sanity @author_Annapoorna
  Scenario:Verify if the jackpot application can be accessed by users
    When I send a GET request to jackpot endpoint with query param "GBP"
    Then I should get back a valid status code 200


  @author_Annapoorna
  Scenario Outline: Verify if the currency in the response matches the currency requested
    When I send a GET request to jackpot endpoint with "<currency>"
    Then I should see the "<currency>" in the response body

    Examples:
      | currency |
      | GBP      |
      | EUR      |
      | SEK      |

  Scenario Outline: Verify if the id of jackpot is associated with correct name
    When I send a GET request to jackpot endpoint with query param "GBP"
    Then I should see names from sheet name "<sheetName>" and row number "<rowNumber>" associated with id
    Examples:
      | sheetName | rowNumber |
      | Sheet1    | 0         |
      | Sheet1    | 1         |
      | Sheet1    | 2         |
      | Sheet1    | 3         |
      | Sheet1    | 4         |
      | Sheet1    | 5         |
      | Sheet1    | 6         |

