![Build](https://img.shields.io/circleci/build/github/pi4-assister/backend/develop?token=e793b2585608568bd63bfff7a5ddcbac3118c38c)
![Java version](https://img.shields.io/badge/java-8-blue)
![Spring Version](https://img.shields.io/badge/Spring%20Boot-v2.3.3.RELEASE-brightgreen)
![GitHub contributors system](https://img.shields.io/github/contributors/pi4-assister/backend)
![GitHub last commit system](https://img.shields.io/github/last-commit/pi4-assister/backend)

<br />
<p align="center">
  <a href="https://i.imgur.com/yrokMGF.png">
    <img src="https://i.imgur.com/yrokMGF.png" alt="Assister Application" width="200">
  </a>

  <h3 align="center">Assister Application Backend</h3>

  <p align="center">
   CENTRO UNIVERSITÁRIO SENAC - SANTO AMARO - SISTEMAS DE INFORMAÇÃO 2020 4 SEMESTRE
    <br />
    <a href="https://pi4-assister.atlassian.net/wiki/spaces/AA/overview"><strong>Dev's Docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/pi4-assister/backend/issues">Click here to report a bug</a>
    · or ·
    <a href="https://github.com/pi4-assister/backend/issues">click here to help us with refactor and features</a>
  </p>


## Contents

* [About Assister Application](#about-assister-application)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [About us](#about-us)
* [Technologies used](#technologies-used)

## About Assister Application

Assister Application is a website whose focus is to help people with some type of disability or elderly to do their daily tasks easily.
It has a Business-to-Business-to-Customer strategy (like other apps like Uber, iFood, etc) where we have two customers: CLIENT and ASSISTER.

Assister is the type of Customer who will help other people (like uber drivers) by offering specialized service that will be registered in our database.

Client is the type of Customer who will search for an Assister and then will be guided.

### Built With
* [Java 8](https://openjdk.java.net/install/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Gradle](https://gradle.org/)

## Getting Started

### Prerequisites


This is almost a "ready-to-use" system. The only thing you will need is the openJDK 8 with gradlew.
* openJDK
```sh
sudo apt-get install openjdk-8-jdk
```

### Installation

Using [IntelliJ](https://www.jetbrains.com/idea/download/#section=linux) it will make sure all gradle dependencies is fine.

Then you will need to override the application.properties template.

After all settings are done, use this command to run the application:
```sh
./gradlew bootRun
```

Voilà! :)

## About us

Authors:
* Alessandro Ciambarella
    * [LinkedIn](https://www.linkedin.com/in/aleciambarella/)
    * [Github](https://github.com/aleciambarella)
* Pedro Henrique Barricelli Martins
    * [LinkedIn](https://www.linkedin.com/in/pedrohbmartins/)
    * [Github](https://github.com/eopit)
* Rafael Fernandes Narbutis
    * [LinkedIn](https://www.linkedin.com/in/rafael-fernandes-narbutis-b47563165/)
    * [Github](https://github.com/rafaelnarbutis)

## Technologies used

* [GCP](https://cloud.google.com/)
  * [STORAGE](https://cloud.google.com/storage)
  * [MySQL](https://cloud.google.com)
* [Heroku](https://heroku.com)
* [MySQL](https://www.mysql.com/)
* [CI/CD](https://circleci.com/)
