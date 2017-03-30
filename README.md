# The voting system for restaurants quality 

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/6949a1de2a5a4185ba09c5968e2d54cc)](https://www.codacy.com/app/Alexey-SimonovOrganization/votingSystem?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=simonovAlexey/votingSystem&amp;utm_campaign=Badge_Grade)
[![Dependency Status](https://dependencyci.com/github/simonovAlexey/votingSystem/badge)](https://dependencyci.com/github/simonovAlexey/votingSystem)

Design and implement a UI and JSON API using Hibernate/Spring/SpringMVC.

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

 Used tools, libraries and frameworks:
  <a href="http://maven.apache.org/">Maven</a>,
  <a href="http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mvc.html">Spring MVC</a>,
  <a href="http://projects.spring.io/spring-security/">Spring Security</a>,
  <a href="http://projects.spring.io/spring-data-jpa/">Spring Data JPA</a>,
  <a href="http://spring.io/blog/2014/05/07/preview-spring-security-test-method-security">Spring Security Test</a>,
  <a href="http://hibernate.org/orm/">Hibernate ORM</a>,
  <a href="http://hibernate.org/validator/">Hibernate Validator</a>,
  <a href="http://www.slf4j.org/">SLF4J</a>,
  <a href="https://github.com/FasterXML/jackson">Json Jackson</a>,
  <a href="http://ehcache.org">Ehcache</a>,
  <a href="http://hsqldb.org//">HSQLDB</a>,
  <a href="http://junit.org/">JUnit</a>,
  <a href="http://tomcat.apache.org/">Apache Tomcat</a>.
