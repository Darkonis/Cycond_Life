Account API:

	GET:
	"/api/accounts" - Returns all accounts
	"/api/accounts/{accountId}" - Returns account with given accountId
	"/api/accounts/getByUsername/{username}" - Returns account with given username
	"/api/accounts/getByEmail/{email}" - Returns account with given email
	
	POST:
	"/api/accounts" - Creates an account from the include JSON object with
		 the format below. Replace ALL CAP parts with custom data. Returns new account
			{			    
			    "username": "USERNAME",
			    "password": "PASSWORD",
			    "firstName": "FIRST NAME",
			    "lastName": "LAST NAME",
			    "email": "EMAIL",			    
			    "accountType": "ACCOUNT TYPE FROM {admin, moderator, player}"
			}
	PUT:		
	"/api/accounts"	- Updates the account row with the given account ID included in the JSON object. Returns echoed changes
			{			 
				"accountId": "ACCOUNT ID # TO UPDATE"  
			    "username": "USERNAME",
			    "password": "PASSWORD",
			    "firstName": "FIRST NAME",
			    "lastName": "LAST NAME",
			    "email": "EMAIL",			    
			    "accountType": "ACCOUNT TYPE from {admin, moderator, player}"
			}
	
	DELETE:
	"/api/accounts/{accountId}" - Deletes account with given accountId (currently doesn't delete any rows from associated tables)
	
	


	
	
	
	
