Feature: Reset functionality on login page of Application 		

Scenario: Verification of Reset button 
Given Open the Firefox and launch the application			
When Enter the Username "username12" and the Password "password12"			
Then Reset the credential			

Scenario Outline: Verification of reset button with numbers of credential
Given Open the Firefox and launch the application				
When Enter the <username> and <password>
Then Reset the credential						

Examples:                      		
|username  |password         |		
|User1     |password1        |
|User2     |password2        |
|User3     |password3        |
|User4     |password4        |


