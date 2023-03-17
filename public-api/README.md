# price comparison public api


## Project setup
```

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

docker tag sl-public-api:latest XXXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-public-api:latest-prod
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin XXXXX.dkr.ecr.eu-west-1.amazonaws.com
docker push XXXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-public-api:latest-prod
```

### Run your unit tests
```

```

### Lints and fixes files
```

```
