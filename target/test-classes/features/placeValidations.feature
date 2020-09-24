Feature: Validating Place API's
@AddPlace @Regression
Scenario Outline: Verify is Place is being successfully added using AddPlaceAPI
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "addPlaceAPI" with "post" http request
	Then the API call got success with Status Code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place-Id created maps to "<name>" using "getPlaceAPI"

Examples:
	| name 	   | language | address 		   |
	| AA House | English  | World Cross Center |
#	| BB house | Spanish  | Sea Cross Center   |

@DeletePlace @Regression
Scenario: Verify if delete Place functionality is working
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "post" http request
	Then the API call got success with Status Code 200
	And "status" in response body is "OK"