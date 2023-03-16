# price-comparison-client1

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
```

### Run your unit tests
```
npm run test:unit
```

### Lints and fixes files
```
npm run lint
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


### Docker
Build frontend container: https://price-comparison-system-frontend.azurewebsites.net/
~~~
docker build -t aht0/price-comparison-system-vuejs:latest .
docker images
docker tag acc58fa1ec8e aht0/price-comparison-system-vuejs:latest
docker push aht0/price-comparison-system-vuejs:latest
~~~
