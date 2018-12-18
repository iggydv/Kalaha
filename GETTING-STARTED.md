# Kalaha

Please see [README.md](https://github.com/iggydv/Kalaha/README.md) for game rules.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- [JDK 1.8+](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) 
- [Maven 3+](https://maven.apache.org/install.html)
- (optional) Docker 17.06+ [Ubuntu](https://docs.docker.com/install/linux/docker-ce/ubuntu/), [MacOS](https://docs.docker.com/docker-for-mac/install/#install-and-run-docker-for-mac), [Windows](https://docs.docker.com/docker-for-windows/install/)


### Installing
Navigate to the Kalaha project 
```
cd ~/Kalaha
```

To create the runnable `JAR` file , the following steps should be followed:<br/>
Execute a `maven clean package`, to clean all previous builds and take the compiled code and package it in a `JAR` file.<br/>
After the code has been packaged, execute the maven install command, to install the package into the local repository, for use as a dependency in other projects locally.

```
mvn clean package
mvn install
```
And the following output should be displayed
```
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary:
[INFO] 
[INFO] kalaha ............................................. SUCCESS [  0.150 s]
[INFO] kalaha-utils ....................................... SUCCESS [  0.408 s]
[INFO] kalaha-model ....................................... SUCCESS [  1.183 s]
[INFO] kalaha-service ..................................... SUCCESS [  0.669 s]
[INFO] kalaha-ui .......................................... SUCCESS [  1.028 s]
[INFO] kalaha-web ......................................... SUCCESS [  0.819 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.477 s
[INFO] Finished at: 2018-12-18T10:43:48+02:00
[INFO] Final Memory: 28M/619M
[INFO] ------------------------------------------------------------------------
```
The `Kalaha` project is now ready to be deployed


End with an example of getting some data out of the system or using it for a little demo

## Running the tests

During the maven `clean` `install` and `package` lifecycles unit tests are executed, but if you would like to execute them seperately you can execute the following command.

```
mvn test
```
All unit test results can be found on the console output <br/>
The following output should be displayed
```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.ignatius.object.tests.PlayerTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.355 s - in com.ignatius.object.tests.PlayerTest
[INFO] Running com.ignatius.object.tests.PitTest
[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 s - in com.ignatius.object.tests.PitTest
[INFO] Running com.ignatius.object.tests.BoardTest
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.
[INFO] Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.005 s - in com.ignatius.object.tests.BoardTest
.
. <OTHER UNIT TEST RESULTS>
.
[INFO] Reactor Summary:
[INFO] 
[INFO] kalaha ............................................. SUCCESS [  0.002 s]
[INFO] kalaha-utils ....................................... SUCCESS [  0.447 s]
[INFO] kalaha-model ....................................... SUCCESS [  1.237 s]
[INFO] kalaha-service ..................................... SUCCESS [  0.545 s]
[INFO] kalaha-ui .......................................... SUCCESS [  1.121 s]
[INFO] kalaha-web ......................................... SUCCESS [  0.240 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.871 s
[INFO] Finished at: 2018-12-18T11:36:08+02:00
[INFO] Final Memory: 20M/397M
[INFO] ------------------------------------------------------------------------
```



## Execution

### JAR

Navigate to the location of the `kalaha-web-1.3.6.RELEASE.jar` file

```
cd ~/Kalaha/kalaha-web/target
```
To start up the `Kalaha Web Application`, execute the `kalaha-web-1.3.6.RELEASE.jar` file
```
java -jar kalaha-web-1.3.6.RELEASE.jar
```
```
<It will produce something similar to the following>

17:31:43.497 [main] INFO com.ignatius.launcher.KalahaWebApplication - Initializing Kalaha web application

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.3.6.RELEASE)

2018-12-17 17:31:43.907  INFO 18800 --- [           main] c.i.launcher.KalahaWebApplication        : Starting KalahaWebApplication v1.3.6.RELEASE on ignatius-Dell with PID 18800 (/home/ignatius/Projects/personal projects/Kalaha/kalaha-web/target/kalaha-web-1.3.6.RELEASE.jar started by ignatius in /home/ignatius/Projects/personal projects/Kalaha/kalaha-web/target)
.
.
.

```
In your web browser of choice, navigate to `http://localhost:8080/kalaha` and ENJOY!

To stop the execution you can use `CTRL+C` in your terminal window or navigate to `http://localhost:8080/quit`

### DOCKER

If you want  to build the docker image locally, you can do so by following these steps:
* Navigate to the root `Kalaha` directory
  ```
  cd Kalaha/
  ```
* Build the `JAR` files by executing the steps mentioned above 
  ```
  mvn clean package
  mvn install
  ```
* Execute the `docker` build
  ```
  docker build -t 'kalaha' .
  ```
* Creating the docker container is just as easy
  ```
  docker run -d --restart=always -p 8080:8080 kalaha
  ```
In your web browser of choice, navigate to http://localhost:8080/kalaha and ENJOY!

To stop and destroy the container you can execute the following commands
* List active containers
  ```
  docker ps
  ```
* Stop the `Kalaha` container 
  ```
  docker stop <CONTAINER ID>
  ```
* Remove the `Kalaha` container
  ```
  docker rm <CONTAINtER ID>
  ```

#### Pull from Dockerhub

If you do not wish to build the image locally you can pull and run the image from dockerhub
```
docker run -d --restart=always -p 8080:8080 iggydv/kalaha
```

## Screenshots

Please see [SCREENSHOTS.md](https://github.com/iggydv/Kalaha/blob/v2/SCREENSHOTS.md) for Web Application screenshots.

## Built With

* [Spring Boot](http://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Vaadin](https://vaadin.com/) - Used to generate UI components


## Known issues and Improvements

### Issues

* The docker image currently cannot close the application context via the `http://localhost:8080/quit` endpoint
* Consuming the `http://localhost:8080/quit` in the UI causes the project to hang and does not close the web application correctly

## Improvements
* Support multiple sessions on one kalaha board
  * This would mean exposing the `Kalaha` web application to the internet
  * Allow `2` players to play against one another
  * Any other user to access the endpoint will be a spectator
* We could increase the competitiveness of the game by:
  * Adding a timer to each players turn
  * Creating AI to play against a user
* General UI improvements

## Author

* **Ignatius de Villiers**

## Contact
* iggydv12@gmail.com

## License

This project is free to use, any suggetions and feedback is very welcome!

