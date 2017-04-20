### Test MealRestController (application deployed in application context `votingSystem`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/votingSystem/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100004
`curl -s http://localhost:8080/votingSystem/rest/admin/users/100004 --user admin@gmail.com:admin`

#### get All Restaurants
`curl -s http://localhost:8080/votingSystem/rest/restaurants --user admin@gmail.com:admin`

#### get Menu for Restaurant 100000
`curl -s http://localhost:8080/votingSystem/rest/admin/restaurants/menu=100000 --user admin@gmail.com:admin`

#### get All Meals for Rest 100001
`curl -s http://localhost:8080/votingSystem/rest/dishes/100001 --user admin@gmail.com:admin`

#### get Meals 100008 for Rest 100001
`curl -s http://localhost:8080/votingSystem/rest/profile/dishes/100001/100008  --user admin@gmail.com:admin`

#### get Meals not found
`curl -s -v http://localhost:8080/rest/dishes/100001/100011 --user admin@gmail.com:admin`

#### delete Meals
`curl -s -X DELETE http://localhost:8080/rest/dishes/100001/100010 --user admin@gmail.com:admin`

#### create Meals
`curl -s -X POST -d '{"description":"Created lunch","price":300}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/votingSystem/rest/dishes/100001 --user admin@gmail.com:admin`

#### update Meals
`curl -s -X PUT -d '{"description":"Created lunch","price":300,"inMenu":"true"}' -H 'Content-Type: application/json' http://localhost:8080/votingSystem/rest/dishes/100001/100009 --user admin@gmail.com:admin`