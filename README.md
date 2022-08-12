# Kinsta - Hello World - Java
An example of how to set your Java application up to enable deployment on Kinsta App Hosting services.

> Kinsta’s Application Hosting is a service to run your web apps and any databases side by side in a hassle-free environment, tailored for developer needs and ease of use. 
App Hosting is currently in an invite-only beta phase, sign up for a test account at [kinsta.com/app-hosting](https://kinsta.com/app-hosting).

## Dependency Management

During the deployment process Kinsta will automatically install dependencies defined in your pom.xml file.

## Web Server Setup

### Port
Kinsta automatically sets the `PORT` environment variable. You should **not** define it yourself and you should **not** hard-code it into the application. Use 
`System.getenv("PORT")` in your code when referring to the server port. 

### Start Command
When deploying an application Kinsta will automatically create processes based on the Procfile in the root of the repository. Make sure to use this command to run your 
server.

```
web: java -jar target/kinsta-java-hello-world-1.0-SNAPSHOT.jar
```

Make sure that your main class is defined in the pom.xml file.
