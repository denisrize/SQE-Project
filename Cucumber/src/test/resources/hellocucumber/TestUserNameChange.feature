Feature: Test Client tries to add product to wish list when product no longer exist

  Scenario: Admin deactivate user while user logged in and tries to add item to wish list.
    Given a client is login with email "admin@gmail.com" and password "1234"
    And the admin login with "admin" and "pop123" to his account
    And Client is on the Edit Account page trying to update his name to "Bye"
    And the admin has deactivated the user
    When Client click to submit the name change
    Then the user name was not changed