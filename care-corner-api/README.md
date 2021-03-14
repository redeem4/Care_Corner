# Care Corner API

## Setting Up

 Requirements:
  - Docker

### Docker

Docker is used to run [Localstack](https://github.com/localstack/localstack) and the [Serverless Framework](http://www.serverless.com) in a cross-platform way: 
 
  https://docs.docker.com/get-docker/

Head over to your command line to build the care-corner-api docker image:

    cd care-corner-api
    docker-compose build

  Start up the care corner containers:

    docker-compose up -d

  _Note_: The `-d` runs the docker process in the background. You can view the docker
  logs on the command line or in the docker dashboard. If you drop the `-d`, you can view the logs as it runs, but will need to use another terminal to execute other things.

  _Note_: on MacOS you may have to run `TMPDIR=/private$TMPDIR docker-compose up`

  Now connect to the care-corner-api docker container:

      docker exec -it care-corner-api /bin/bash

  Your command prompt should look similiar to:

      root@9977a41a461c:/care-corner-api#

  You are now connected to the docker container that contains the care-corner-api development environment. 

## Developing

  When you first build the container or anytime the javascript dependencies change you should bundle the deps: 

      yarn

  When you first build the container or change any of the handler code, you build the java via:

      mvn clean install


  When developing, you should use lambda emulation feature of serverless:

    sls invoke local --function panic

 Tip: `sls` is an alisas provided for the `serverless` command.

 To run with test data, use the `d` or `p` switch:

      sls invoke -f panic -p data/panic.json --stage local


""Serverless: Building Java bridge, first invocation might take a bit longer.""

To log your requests verbosely to troubleshoot, add the 'l' switch:

    export SLS_DEBUG=*
    sls invoke local -f panic -l

    sls invoke -f panic -l --stage local

    sls logs -f panic


      sls deploy --stage local

  To check that the deploy was successful:

      sls info --stage local

 Example:

```
Service Information
service: care-corner-api
stage: local
region: us-east-1
stack: care-corner-api-local
resources: 11
api keys:
  None
endpoints:
    http://localhost:4566/restapis/xvik4x63dv/local/_user_request_
functions:
  panic: care-corner-api-local-panic
layers:
  None
```

  Once it's deployed on localstack, the URL to the endpoint is formed by
  concatenating the endpoint returned and the function name with a slash in
  between.

  endpoints: http://localhost:4566/restapis/nwpm11x7ci/local/_user_request_
  functions: panic

  result: http://localhost:4566/restapis/nwpm11x7ci/local/_user_request_/api/panic


Note: From within docker, you access localstack via the localstack name, while
on your host you reference via localhost.

    curl -X POST http://localstack:4566/restapis/nwpm11x7ci/local/_user_request_/api/panic



## Localstack

### Invoking API Gateway

While API Gateway endpoints on AWS use a custom DNS name to identify the API ID
(e.g., https://nmafetnwf6.execute-api.us-east-1.amazonaws.com/prod/my/path),
LocalStack uses the special URL path indicator .../_user_request_/... to indicate
the execution of a REST API method.

The URL pattern for API Gateway executions is
http://localhost:4566/restapis/<apiId>/<stage>/_user_request_/<methodPath>.
The example URL above would map to the following localhost URL:

$ curl http://localhost:4566/restapis/nmafetnwf6/prod/_user_request_/my/path

## Localstack

  cd serverless-api-java
      mvn clean install


    awslocal s3api list-buckets

## Deploying

### Localstack

    serverless deploy --stage local


### Production

Serverless is able to deploy to AWS easily.

WIP, will require AWS account access and a bit of prior provisioning.

    sls deploy

