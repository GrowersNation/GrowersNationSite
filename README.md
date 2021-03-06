# Growers' Nation Site

This project provides the RESTful API to serve web clients with OpenID authentication.

Resources supported are:

* Provision of soil data from BGS sources
* Acceptance of user-specific soil data
* User account creation and maintenance
* Input of sensor data as JSON from client applications

## Technology

This project uses the [Dropwizard](http://dropwizard.codahale.com) Java web application development framework. 
This is a very lightweight and highly scalable approach that facilitates Agile development practices.

All builds are done with Maven and the endpoints serve JSON. 

The application relies on a running instance of MongoDB for Growing Degree Days (GDD) data but
the soil data is available from the British Geological Survey (BGS) servers.

## Notation

<project root> - The root directory of the project as checked out through git

All commands will work on *nix without modification, use \ instead of / for Windows.

## Getting started

From the console you can do the following

    cd <project root>
    mvn clean install
    java -jar target/growers-nation-develop-SNAPSHOT.jar server gn.yml

## Verify endpoints

Running locally, try the following links (the port is set in the `gn.yml` file): 

* [Show a HTML site](http://localhost:9090/)
* [Show some soil data](http://localhost:9090/soildata?location=51.65,0.1)

# Installing MongoDB

Follow the usual [MongoDB installation instructions](http://docs.mongodb.org/manual/installation/), such as

```
$ brew update
$ brew install mongo
```

Start MongoDB as a background process with

```
$ mongod &
```

Then create the following collections through the Mongo CLI

```
$ mongo
> use gn
> db.createCollection("soil-data");
> exit
```

## Proxy settings

If you are behind a firewall you will need to set the proxy. This is configured in the YAML file.

## Where does the ASCII art come from?

The ASCII art for the startup banner was created using the online tool available at
[Webestools](http://www.webestools.com/ascii-text-generator-ascii-art-code-online-txt2ascii-text2ascii-maker-free-text-to-ascii-converter.html)
with a font of Tiza
