
@tag
Feature: purchase the order 
  I want to use this template for my feature file

	Background:
	Given I land on landing page
  @tag2
  Scenario Outline: sumbitting the order
    Given I logged in with <username> and <password>
    When I add the <productName> from the cart
    And checkout <productName> and submit the order
 		Then "THANKYOU FOR THE ORDER." message is displayed.

    Examples: 
      | username  					| password 		| productName|
      | bharath@yopmail.com | Test@1234   | ZARA COAT 3|
      
 

