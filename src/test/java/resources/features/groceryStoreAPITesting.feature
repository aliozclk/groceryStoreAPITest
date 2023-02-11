Feature: Grocery Store End to End Test

  Scenario Outline: ...

    #change given variables with excel file names
    Given the user wants to test test case : "<TestCase>" by retrieving the test data from Excel Workbook: "API_TestData" Sheet: "Payment" for API
    When the status "UP"
    Then the user gets all products with
    And the user creates a cart with
    And the user adds an item to cart with
    And the user modifies an item by determining quantity
    And the user gets token
    And the user creates an order

    Examples:
      | TestCase    |  |
      | Test_Case_1 |  |
      | Test_Case_2 |  |
      | Test_Case_3 |  |