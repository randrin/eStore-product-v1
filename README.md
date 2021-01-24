---
**** Add External configuration file ****
---
1) Create the application jar (mvn clean package)
2) Go where the application far is created (cd ..)
3) Run the application jar with the configuration file (java -jar APPLICATION_NAME.jar --spring.config.additional-location=PATH_OF_FILE)

---
**** Log request / reponse ****
---
- Possible to see the request/reponse in terminal console
- Possible to hide confidential informations before sending/receiving request/respone

---
**** Actuator for monitoring service ****
---
1) Add the dependency actuator starter
2) Restart the application and go to loacalhost:port/actuactor
3) Configure the application.yaml for the Business Logic

---
**** Registration Microservice in Admin Server  ****
---
1) Add the dependency admin client
2) Configure the application.properties (application.yaml) registering withe the url of admin server