# Care Corner API

## Setting Up

 Requirements: 
  - Node
  - Npm
  - Yarn
  - Serverless
  - Java 11 SDK
  - Maven

### Node/Npm/Yarn/Serverless

Install Volta to help with cross-platform support:

    https://docs.volta.sh/guide/getting-started

Install node, npm, serverless, and yarn:

    volta install node
    volta install npm
    volta install yarn
    volta install serverless

### Java/Maven

Install the Java 11 SDK, not the JRE.
I recommend managing in a cross-platform fashion via Sdkman: 
  
    https://sdkman.io/ 

Install Java, Maven:

    sdk install java
    sdk install maven


Install node packages:

    cd serverless-api-java
    yarn

## Building

    cd serverless-api-java
    mvn clean install

## Running

    cd serverless-api-java
    sls invoke local --function panic

 Tip: `sls` is an alisas provided for `serverless`. 

 To run with test data, use the `d` or `p` switch:

      sls invoke local -f panic -p data/panic.json

To log your requests verbosely to troubleshoot, add the 'l' switch:

    export SLS_DEBUG=*
    sls invoke local -f panic -l

    sls logs -f panic

Note: There is a potential issue with the Java invoker depending on how
your specific machine is setup.

If your command line hangs with this message, 
"Serverless: Building Java bridge, first invocation might take a bit longer." 
Do the following:

  cd .volta/tools/image/packages/serverless/lib/node_modules/serverless/lib/plugins/aws/invokeLocal/runtimeWrappers/java
  mvn package

See the respective Github Issues:

  - https://github.com/serverless/serverless/issues/5030
  - https://github.com/serverless/serverless/issues/8859

## Deploying

Serverless is able to deploy to AWS easily. 

WIP, will require AWS account access and a bit of prior provisioning. 

    sls deploy

