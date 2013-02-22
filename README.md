# Growers' Nation Site

This project provides the RESTful API to serve web clients with OpenID authentication.

## Notation

<project root> - The root directory of the project as checked out through git

All commands will work on *nix without modification, use \ instead of / for Windows.

## Manual installation of eXistDB driver files

The eXistDB support requires that `exist.jar` and `exist-optional.jar` are installed as Maven artifacts before a 
build can complete. Use the following commands from your eXistDB installation (usually `/opt/exist`):

    mvn install:install-file -Dfile=./exist.jar -DgroupId=org.exist -DartifactId=exist -Dversion=2.0.0 -Dpackaging=jar
    mvn install:install-file -Dfile=./exist-optional.jar -DgroupId=org.exist -DartifactId=exist -Dversion=2.0.0 -Dpackaging=jar

Then switch into `/opt/exist/lib/core` and install the `xmldb.jar`

    mvn install:install-file -Dfile=./xmldb.jar -DgroupId=org.xmldb -DartifactId=xmldb -Dversion=2.0.0 -Dpackaging=jar


That should be a one-off operation for each build machine.

## Getting started

From the console you can do the following

    cd <project root>
    mvn clean install
    java -jar target/growers-nation-develop-SNAPSHOT.jar server gn.yml

## Proxy settings

If you are behind a firewall you will need to set the proxy. This is configured in the YAML file.

## Where does the ASCII art come from?

The ASCII art for the startup banner was created using the online tool available at
[Webestools][http://www.webestools.com/ascii-text-generator-ascii-art-code-online-txt2ascii-text2ascii-maker-free-text-to-ascii-converter.html]
with a font of Tiza
