 

____
# HousePricePredictor

HousePricePredictor helps to predict the price of the house based on the "RandomForest" Algorithm. As learning model a sample learning model is provided based on which the house price is predicted with the different attributes.
The data are gathered for city of Tirana/Albania

## Getting Started

`HousePricePredictor` is a Spring Boot based project with Maven support. It can be built with the following commands :

```
$ mvn clean install
$ mvn spring-boot:run
```

## Running the tests
```
$ mvn clean test
```

## Demo

#### UI
`HousePricePredictor` is hosted on a [heroku server](https://price-predictor-tirana-app.herokuapp.com/) with a sample learning model file.

#### API
To use the API of `HousePricePredictor`, the following URL, Header and JSON body can be used:

* Request URL
```
[POST] http://localhost:8080/v1/house 
```
* Header
```
Content-Type:application/json
```
* Body
```
{
sizeMeters: numeric real
furnished:  {yes, no}
hasLift:  {yes, no}
ownerType:  {individual, estate}
legalized:  {me_hipoteke, pa_hipoteke}
buildingyear:  numeric
city:  {one_plus_one, two_plus_one}
algorithm:
price numeric   
  
}
```

# Authors
* **Amir Buzo** 

## Built With

* [Weka](https://www.cs.waikato.ac.nz/ml/weka/) - The Machine Learning framework used.
* [SpringBoot](https://spring.io/projects/spring-boot) - The Rest API framework used.
* [Maven](https://maven.apache.org/) - Dependency Management.
* [AngularJS](https://angularjs.org/) - The UI framework used.
* [Bootstrap](https://getbootstrap.com/) - The UI framework used.
