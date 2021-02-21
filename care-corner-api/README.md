# Care Corner API


## Setting Up

### AWS SAM

The AWS Serverless Application Model (AWS SAM) is an open-source framework
that you can use to build serverless applications on AWS.

Install the AWS SAM CLI:

    https://docs.aws.amazon.com/serverless-application-model/latest/developerguide/serverless-sam-cli-install.html




Install Serverless:

Install Node and npm:



https://www.serverless.com/blog/how-to-create-a-rest-api-in-java-using-dynamodb-and-serverless


 https://www.serverless.com/blog/how-to-create-a-rest-api-in-java-using-dynamodb-and-serverless


 https://github.com/localstack/localstack



### Creating new Serverless projects


Java: https://www.serverless.com/blog/how-to-create-a-rest-api-in-java-using-dynamodb-and-serverless

Python: https://www.serverless.com/framework/docs/providers/aws/examples/hello-world/python/

https://volta.sh/

  https://docs.volta.sh/guide/getting-started

  volta install node
  volta install npm
  volta install serverless
  volta install yarn

Install java SDK, not JRE, I recoommend sdkman: https://sdkman.io/
Install maven, wink sdkman

  Now, node, npm, yarn, and serverless should be available in any project.

  Tip: sls is short for serverless.


Install packages:
    cd serverless-api-java

    yarn

Issue with java serverless, one time thing:
${NPM_DIR}/node_modules/serverless/lib/plugins/aws/invokeLocal/java and run mvn package

## Running

    mvn clean install

    npx sls invoke local --function panic


## Deploying

    sls deploy

