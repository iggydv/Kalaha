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
At the following output should be displayed
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

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Execution

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


## Built With

* [Spring Boot](http://spring.io/projects/spring-boot) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Vaadin](https://vaadin.com/) - Used to generate UI components


## Versioning

For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Author

* **Ignatius de Villiers**

## Contact
* iggydv12@gmail.com

## License

This project is free to use, any suggetions and feedback is very welcome!

