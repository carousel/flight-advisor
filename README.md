# Flight Advisor application
Author:
miroslav.trninic@gmail.com

## Prerequisites
    Java version >= 1.8.0_251
    Apache Maven version >= 3.6.3 

## How to run application
Clone repository from github: 
git@github.com:carousel/flight-advisor.git
Or
https://github.com/carousel/flight-advisor
From command line run:

    mvn spring-boot:run

## Additional explanation
Application is using H2 in-memory database
Initial admin user will be seeded and roles applied on startup

    username: admin
    password: letmein

Couple of cities with comments will be also seeded

Application is using JWT token for signin, so general user needs to signup first, in order to use it
Postman collection is attached, for more convenient API usage

Couple of simple unit tests are added (JUnit5)

Swagger documentation can be viewed on:
http://localhost/swagger-ui.html
Othere than that, code is well documented and pretty self explanatory.

Logging storage is present (logs directory)

Following directories are not .gitignored due to nature of applications(demo):

    logs
    docs

Salt is added to user credentials, but according to this SO thread, it is auto created by Spring:
https://stackoverflow.com/questions/6832445/how-can-bcrypt-have-built-in-salts
    This can be be verified by looking at Spring BCrypt source code:
```java
 public static String gensalt(String prefix, int log_rounds, SecureRandom random) throws IllegalArgumentException {
        StringBuilder rs = new StringBuilder();
        byte[] rnd = new byte[16];
        if (!prefix.startsWith("$2") || prefix.charAt(2) != 'a' && prefix.charAt(2) != 'y' && prefix.charAt(2) != 'b') {
            throw new IllegalArgumentException("Invalid prefix");
        } else if (log_rounds >= 4 && log_rounds <= 31) {
            random.nextBytes(rnd);
            rs.append("$2");
            rs.append(prefix.charAt(2));
            rs.append("$");
            if (log_rounds < 10) {
                rs.append("0");
            }

            rs.append(log_rounds);
            rs.append("$");
            encode_base64(rnd, rnd.length, rs);
            return rs.toString();
        } else {
            throw new IllegalArgumentException("Invalid log_rounds");
        }
    }
```


