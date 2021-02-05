---
**** Add External configuration file ****
---
1) Create the application jar (mvn clean package) [do not run the unit test: mvn clean package -xtest]
2) Go where the application jar is created (cd ..)
3) Run the application jar with the configuration file (java -jar APPLICATION_NAME.jar --spring.config.additional-location=PATH_OF_FILE)

---
**** Log request / response ****
---
- Possible to see the request/response in terminal console
- Possible to hide confidential informations before sending/receiving request/response

---
**** Actuator for monitoring service ****
---
1) Add the dependency actuator starter
2) Restart the application and go to loacalhost:port/actuactor
3) Configure the application.yaml for the Business Logic

---
**** Registration Microservice in Admin Server ****
---
1) Add the dependency admin client
2) Configure the application.properties (application.yaml) registering with the url of admin server

---
**** Notifications Microservice in Admin Server ****
---
1) Add the dependency in admin server [spring-boot-starter-mail]
2) Configure the application.properties (application.yaml) registering with the port, host, domain etc.. of the server

---
**** Data Persistence and Clustering with Hazelcast in SBA Server ****
---
1) Add the dependency in admin server [hazelcast]
2) Create the configuration class: to to the link: https://codecentric.github.io/spring-boot-admin/current/#clustering-support
3) Create more instances of server and stop/start one any time. the data persistence don't loss