Spring Boot Coding Dojo
---
This is my technical assignment!

### What I did

As the new engineer leading this project for make it production grade, I did some things sequentially:

first of all I update and upgrade spring dependency in this way I can sure about using fresh and compatible libraries.

then did some refactoring and repackaging. I tried to create and build a structure base on MVC development.

the next thing is important to me is having test methods so I implemented unit test and integration test. 
As a developer when I have test I always be motivate and eager to do refactor continuously.

one of important thing your project should have in production is database version controlling so I use Flyway for this approach.

I believe health api in spring boot is an useful option on production so I active that.

When you provide web service it's important have simple ui to test it in development or even in production so I active Swagger.

And finally for have simple and reliable deploying I dockerized the project.


### How to run and use

first you should build and package jar file with maven.
then, I provided docker file, so you can create an image with this file.
and finally you can change environment variables on the docker compose file and launch it with docker command.
via home page ( port:9091) you can access with swagger to test apis.
