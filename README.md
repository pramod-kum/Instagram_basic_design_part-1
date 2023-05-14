<center>
<h1> 🏚️Instagram basic design part 1</h1>
</center>
<center>
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
</center>
This project is Instagram basic design part 1 built using Spring Boot with Java.

---

## Framework Used
* Spring Boot

---

## Language Used
* Java

---

## Data Model

The All ...ModelClass  is defined inside the model packages which has the following attributes:
   
   inside User Model:-<br>
   
private String firstName; <br>
private String lastName;<br>
private Integer age;<br>
private String email;<br>
private String phoneNumber;<br>
  
   
   
  Product Post:- <br>
   
  private Integer postId;<br>
private Timestamp createdDate;<br>
private Timestamp updatedDate;<br>
private String postData;<br>
  
 AuthenticationToken:- <br>
   
 private Long tokenId;<br>
private String token;<br>
private LocalDate tokenCreationDate;<br>

etc...<br>

   
 

---

## Data Flow

1. The user sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.

4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

---

## Functions used :-

### API Endpoints :-
</br>
<b> signUp </b>

* PostMapping: /signIn 

This endpoint makes a call to method in userService class which is connected to database. In database check token given through API.


* GetMapping: 

This endpoint returns data of specific post based on id through API.


* PutMapping: 

This endpoint makes a call to method in userService class which is connected to database. In database we update user.





---
<b> savePost </b>

* PostMapping: 

This endpoint makes a call to method in PostService class which is connected to database. In database we add a new Post given through API.




---

## JpaRepository extended by Repository class...


We have used JpaRepository as a database to implement CRUD Operations.

---

## 📝Project Summary

I have created Instagram basic design part 1 project.  In this project I have used @OneToOne maping and @manyToOne mapping. the user will have to do SignIn and SignUp first. than  Can save Post, update User.  can get post. etc...

![Alt text](https://media.tenor.com/Ra5kgH_k8yMAAAAj/thank-you-thank-you-heart.gif)
