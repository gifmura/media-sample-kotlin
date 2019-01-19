# media-sample-kotlin

> This project is under development.

This is a sample project for developers interested in Kotlin and Spring Boot.

This project is featuring server-side development using Kotlin.

## Environment

* Kotlin version = '1.2.71'
* SpringBoot version = '2.1.2.RELEASE'

## Preface

This project is intended to be used on Mac OS X or Linux.

And following instructions are only for Mac OS X and IntelliJ IDEA, sorry.

## Usage

*As with this projects, you must have JDK 1.8 or heigher installed.*

First of all, you need to install MySQL.

```bash
brew install mysql
mysql.server start
```

And you need to create db user `sampleuser` with password `changeme` like below.

```bash
CREATE USER 'sampleuser'@'localhost' IDENTIFIED by 'changeme';
GRANT ALL PRIVILEGES ON *.* TO 'sampleuser'@'localhost';
```

And you also need to create db `springdb` and table `message` like below.

```bash
CREATE DATABASE springdb;

use springdb;

CREATE TABLE `message` (
  `id`          BIGINT       NOT NULL AUTO_INCREMENT,
  `content`     VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;
```

Next you need to run gradle task `build/build`.

After that, you need to run gradle task `application/bootRun`.

Now you can visit [localhost:8081](localhost:8081) from your browser.

## References

*Spring Security*
* https://docs.spring.io/spring-security/site/docs/current/guides/html5/helloworld-boot.html

*ktlint*
* https://github.com/shyiko/ktlint


## Licence

<a href="#media-sample-kotlin">Back to top</a>

The MIT License (MIT 2019).
