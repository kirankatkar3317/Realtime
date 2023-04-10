Feature: insideKampus login and logout Test


Background: 
Given open website click on Login link


Scenario Outline:  Test for insideKampus login and logout
Then login using username <email> and password <password> 
Then in login page check login logo is present
And click on logout link
    
Examples:
|email                     | password  |
|kirankatkar3317@gmail.com | Kiran@3317|