# Calendarium

A simple calendar app develop for a internship at Musala Soft JSC during 2021.

## Description

A calendar app with support for planning your schedule. It supports adding new events, updating existing events or deleting events. It can also send notifications before an event so people don't forget to attend their far in the future occasions.

## Getting Started

### Dependencies

* Docker 18.06.0+
* Java 8+
* Maven 3.6.3+

### Building

```
mvn package
```

### Setting up the database

```
cd db
docker-compose up
```

### Executing program

```
java -jar target/Calendarium-1.0.jar
```

## Authors

* [Kaloyan Petkov](https://github.com/DidntRead)
* [Milen Valev](https://github.com/ldak)
* [Martin Minev](https://github.com/MartinIsHandsome)

## Version History

* 1.0
  * Initial Release