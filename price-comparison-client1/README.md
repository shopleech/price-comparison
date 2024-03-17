# price-comparison-client1

## Intro


## Project setup
```
nvm use 18
npm install
```

Edit host in src/http-client.ts

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build

docker build -t sl-client .

docker run -it -p 8888:80 --rm sl-client

cd dist/
aws s3 async ./ s3://my-bucket
```

### Run your unit tests
```
npm run test:unit
```

### Lints and fixes files
```
npm run lint
```
