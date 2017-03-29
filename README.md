# The voting system for quality of restaurants

Design and implement a JSON API using Hibernate/Spring/SpringMVC (or Spring-Boot).

### The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user per day
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md <a href="https://github.com/simonovAlexey/votingSystem/blob/master/config/curl.md">couple curl </a> commands to test REST.

-----------------------------

 
