# IPproiect
proiect Housing price prediction

1. POST https://house-prediction-fii.herokuapp.com/api/v1/create - add a new house and the response is the house(including "recommendedPrice")
example for request body:
{
        "userID": "a17dc1b8-a1be-435e-b645-ae1c42d45277",
        "description": "a doua casa",
        "title": "casa de inchiriat",
        "city": "Iasi",
        "country": "Romania",
        "address": "Strada Arges, nr 2",
        "constructionYear": 1977,
        "noOfRooms": 3,
        "floor": 1,
        "surface": 60,
        "landSurface": 60,
        "noOfBathrooms": 2,
        "houseType": 1,
        "sellType": 1,
        "currentPrice": 400
}
   , where houseType = 0-house, 1-apartment
   , where sellType = 0-selling, 1-renting.

2. POST https://house-prediction-fii.herokuapp.com/api/v1/housesPhotos/create(@RequestParam("file") MultipartFile file, @RequestParam("houseID") - add a photo for an existing house

3. GET https://house-prediction-fii.herokuapp.com/api/v1/housesPhotos/{houseID} - get a house's photos

4. POST https://house-prediction-fii.herokuapp.com/api/v1/update - edit an existing house
- in request body you can have any house proprities that have been modified( for the proprities, check ex. no.1)

5. GET https://house-prediction-fii.herokuapp.com/api/v1/allhouses(@RequestParam int page, @RequestParam int number) - returns the specified number of houses from the page you want

6. GET https://house-prediction-fii.herokuapp.com/api/v1/housedetails - returns the details of a house //geolocation(lat and long will be returned soon in the house details)
- request body:
{ 
"houseID": - the id of the house 
"userID": - the id of the user clicking on the user(not the owner!!) so that the views history can be updated
}
!! Use only the houseID in body if the use is not clicking in specifically on the house to view the details but rather you need any details. Example of response:
{
"houseID": "7509bdbf-249f-463c-95f7-f55a314cf500",
    "userID": "a17dc1b8-a1be-435e-b645-ae1c42d45277",
    "description": "a doua casa",
    "title": "casa de inchiriat",
    "city": "Iasi",
    "country": "Romania",
    "address": "Strada Arges, nr 2",
    "area": "gara",
    "constructionYear": 1977,
    "noOfRooms": 3,
    "floor": 1,
    "surface": 60,
    "landSurface": 60,
    "noOfBathrooms": 2,
    "houseType": 1,
    "sellType": 1,
    "priceHistory": {
        "2021-05-09T07:43:53.475+00:00": 400.0
    },
    "favoriteHistory": {},
    "noOfFave": 0,
    "recommendedPrice": 423.43878,
    "currentPrice": 400.0,
    "creationDate": "2021-05-09T07:43:55.029+00:00",
    "viewsHistory": {},
    "views": 1
}

7. GET https://house-prediction-fii.herokuapp.com/api/v1/houseviews/{houseId} - get the number of views a house has

8. GET https://house-prediction-fii.herokuapp.com/api/v1/sellerhouses/{userid} - get all the houses a seller has

9. DELETE https://house-prediction-fii.herokuapp.com/api/v1/delete/{houseid} - delete a house

10. GET https://house-prediction-fii.herokuapp.com/api/v1/lastadded - get the last 9 added houses(for the carousel)

11. GET https://house-prediction-fii.herokuapp.com/api/v1/filter/bysearch/{words} - search(through address and description) filter; the words must be separated through "-"

12. GET https://house-prediction-fii.herokuapp.com/api/v1/filter/byfields - returns a list of houses filtered by fields

13. GET https://house-prediction-fii.herokuapp.com/api/v1/users/history(@RequestParam UUID userID) - returns this user's viewing history

14. PUT

 1) https://house-prediction-fii.herokuapp.com/api/v1/users/addtofavorite
 - request body:
{ 
"houseID": - the id of the house to be added to favorites list  
"userID": - the id of the user who adds the house to his list
}

2)https://house-prediction-fii.herokuapp.com/api/v1/users/addtofavorite/{userid}/{houseid}

15. PUT
 1) https://house-prediction-fii.herokuapp.com/api/v1/users/removefromfavorite
 - request body:
{ 
"houseID": - the id of the house to be removed from favorites list  
"userID": - the id of the user who removes the house from his list
}

2)https://house-prediction-fii.herokuapp.com/api/v1/users/removefromfavorite/{userid}/{houseid}

16. GET https://house-prediction-fii.herokuapp.com/api/v1/users/getfavorite/{userid} - get the favorites list for the user

//User related functions to be updates after back-end integration
