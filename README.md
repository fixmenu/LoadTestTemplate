# README #

Spring Boot demo service 2 accompanying source code for blog post at [http://tech.asimio.net/2016/12/27/Troubleshooting-Spring-RestTemplate-Requests-Timeout.html](http://tech.asimio.net/2016/12/27/Troubleshooting-Spring-RestTemplate-Requests-Timeout.html)

### Requirements ###

* Java 8
* Maven 3.3.x

### Building the artifact ###

```
mvn clean package
```

### Running the application from command line ###

```
mvn spring-boot:run -Dserver.port=8900
```

### Available URLs

```
http://localhost:8900/delegate/demo
```
should result in successful responses.

### Who do I talk to? ###

* ootero at asimio dot net
* https://www.linkedin.com/in/ootero