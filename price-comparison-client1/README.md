# price-comparison-client1

## Intro


## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build

cd dist/
docker build --build-arg APP_ENV=production -t sl-client .

docker run -it -p 8888:80 --rm sl-client

docker tag sl-client:latest XXXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-client:latest-prod
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin XXXXX.dkr.ecr.eu-west-1.amazonaws.com
docker push XXXXX.dkr.ecr.eu-west-1.amazonaws.com/sl-client:latest-prod
```

### Run your unit tests
```
npm run test:unit
```

### Lints and fixes files
```
npm run lint
```
