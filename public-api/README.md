# price comparison public api


## Project setup

application.properties
```
spring.datasource.url=jdbc:postgresql://localhost:5432/jpa_demo
spring.datasource.username=
spring.datasource.password=
spring.datasource.hikari.schema=public
```

### Compiles and hot-reloads for development
```

```

### Compiles and minifies for production
```
gradle build
gradle bootJar

docker build --build-arg APP_ENV=production -t sl-public-api .

docker run -it -p 8080:8080 --rm sl-public-api

docker tag sl-public-api:latest XXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-public-api:latest-prod
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin XXXX.dkr.ecr.eu-west-1.amazonaws.com
docker push XXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-public-api:latest-prod
```

### Run your unit tests
```

```

### Lints and fixes files
```

```
