@grocerySimpleTestWıthExcel
Feature: Grocery Store End to End Test

  Scenario Outline: Data Driven for API Test


    Given the user wants to test test case : "<TestCase>" by retrieving the test data from Excel Workbook: "grocery" Sheet: "store1" for API
    When the status "UP"
    And the user gets all products and print id
    Then the user creates a cart with
    And the user adds an item to cart with
    And the user modifies an item by determining quantity
    And the user gets token with clientName and clientEmail
    And the user creates an order

    Examples:
      | TestCase   |  |
      | TestCase_1 |  |
      | TestCase_2 |  |
      | TestCase_3 |  |