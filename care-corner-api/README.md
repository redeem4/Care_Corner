# Care Corner API

The Care Corner API development environment consists of two main pieces:

- [Localstack](https://github.com/localstack/localstack)
- [Serverless Framework](http://www.serverless.com)

## Setting Up

 Requirements:
  - Docker

### Docker

Docker is used to run [Localstack](https://github.com/localstack/localstack) and the [Serverless Framework](http://www.serverless.com) in a cross-platform way:

To install Docker:

  https://docs.docker.com/get-docker/

Head over to your command line to build the care-corner-api docker image:

    cd care-corner-api
    docker-compose build

Now, rev up the care corner containers:

    docker-compose up -d

_Note_: The `-d` runs the docker process in the background. You can view the docker
logs on the command line or in the docker dashboard. If you drop the `-d`, you can view the logs as it runs but will need to use another terminal to execute other things.

_Note_: on MacOS you may have to prefix with `TMPDIR=/private$TMPDIR docker-compose up`

This will fire up three containers, localstack, the care corner api, and a mysql
database.

Now connect to the care-corner-api docker container:

      docker exec -it care-corner-api /bin/bash

Your command prompt should look similar to:

      root@9977a41a461c:/care-corner-api#

You are now connected to the docker container that contains the care-corner-api development environment.

## Developing

When you first build the container or anytime the javascript dependencies change, you should bundle the deps:

      yarn

When you first build the container or change any of the handler code, you build the Java with maven:

      mvn clean install

When developing, you should use the lambda emulation feature of serverless.
The following invokes the lambda handler that defines the panic function:

    sls invoke local --function panic

Note: The first time you invoke a function the Java Bridge has to download
and build. If this seems to be taking a long time, you'll see a message that says,
"Building Java bridge, first invocation might take a bit longer",
hit Ctrl-C and then run this script: `./scripts/build-java-bridge.sh`

Tip: `sls` is an alisas provided for the `serverless` command.

To run with test data, use the `d` or `p` switch:

      sls invoke local -f panic -p data/panic.json

You should see the following output:

    2021-03-14 21:47:49  DEBUG PanicHandler:26 - FooBarBaz

The `data/panic.json` is a json file used as input, this can mimic data the API will receive from the mobile app.

The first time you invoke the function, you'll see this message, and it will take a while to build all the dependencies:

    ""Serverless: Building Java bridge, first invocation might take a bit longer.""

To log your invocations verbosely to troubleshoot, add the 'l' switch:

    export SLS_DEBUG=*
    sls invoke local -f panic -l
    sls logs -f panic

## Localstack

Once you are running the containers via `docker compose up`, you can test that Localstack is running by browsing to: `http://localhost:4566/health'.

Your browser should display:

    {"services": {"cloudformation": "running", "cloudwatch": "running", "iam": "running", "sts": "running", "lambda": "running", "logs": "running", "s3": "running", "sns": "running", "apigateway": "running"}}

To deploy the lambda handlers to Localstack, to emulate AWS, deploy with the `local` stage:

      sls deploy --stage local

To check that the deployment was successful:

      sls info --stage local

You should see a response similiar to:

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

Once it's deployed on Localstack, the URL to the API Gateway endpoint is formed by
concatenating the endpoint returned and the function name with a slash in
between.

While API Gateway endpoints on AWS use a custom DNS name to identify the API ID
(e.g., https://nmafetnwf6.execute-api.us-east-1.amazonaws.com/prod/my/path),
LocalStack uses the special URL path indicator .../_user_request_/... to indicate
the execution of a REST API method.

The URL pattern for API Gateway executions is
http://localhost:4566/restapis/<apiId>/<stage>/_user_request_/<methodPath>.

Referencing the output from the `sls info` example above, it would map to:

    endpoints: http://localhost:4566/restapis/xvik4x63dv/local/_user_request_
    functions: panic
    result: http://localhost:4566/restapis/xvik4x63dv/local/_user_request_/api/panic

Note: From within docker, you access Localstack via the `localstack` name, while
on your host (or via the mobile application) you reference via `localhost`.

When accessing within the care-corner-api container:

    curl -X POST http://localstack:4566/restapis/nwpm11x7ci/local/_user_request_/api/panic

From your host computer, use `localhost` instead `localstack`:

    curl -X POST http://localhost:4566/restapis/nwpm11x7ci/local/_user_request_/api/panic

_Note_: Your local repository is mounted in the Docker container, so you can edit the code in your host operating system with whatever editor you'd like, and it will be updated in the container. You then build and execute within the Docker container.

## Summary

### Docker

Building the docker containers, this is only needed when the Dockerfile changes:

      docker-compose build

Running the docker containers:

      docker-componse up

Logging into the care-corner-api dev environment:

      docker exec -it care-corner-api /bin/bash

### Developing

Packaging changes to the javascript dependencies:

      yarn

Compiling and building the Java lamba handlers:

      mvn clean install

Invoking the a lambda handler using the emulation feature:

      sls invoke local --function panic

Invoking with full logging, to troubleshoot issues:

      export SLS_DEBUG=*
      sls invoke local -f panic -l
      sls logs -f panic

 ### Localstack

To ensure localstack is running correctly, browse to:

    http://localhost:4566/health

To deploy lambda handlers to Localstack:

    sls deploy --stage local

To check the deployment information:

    sls info --stage local

To test that a lambda handler is deployed:

    curl -X POST http://localstack:4566/restapis/nwpm11x7ci/local/_user_request_/api/panic

### AWS CLI

  From within the care-corner-api, you can use the `awslocal` command to access the AWS API against localstack.

  For example, to list all the buckts on your Localstack instance:

    awslocal s3api list-buckets

  `awslocal` is the same as the standars `aws` CLI, just defaults to Localstack.

  Reference:

  - [Aws CLI Documentation](https://docs.aws.amazon.com/cli/index.html)
  - [Aws Local CLI Repository](https://github.com/localstack/awscli-local)

### Production

Serverless to deploys to AWS proper easily.

WIP, will require AWS account access and a bit of prior provisioning.

    sls deploy

