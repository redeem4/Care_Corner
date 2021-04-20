FROM node:current-buster as builder

ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y \
    default-mysql-client \
    python-dev \
    python-pip \
    zip \
    unzip && \ 
    rm -rf /var/lib/apt/lists/*

# awscli and awslocal
RUN pip install awscli-local[ver1]

# sdkman; java & maven
ARG JAVA_VERSION="16.0.0.j9-adpt"
ARG MAVEN_VERSION="3.8.1"
RUN curl -o- -L https://get.sdkman.io | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    yes | sdk install java $JAVA_VERSION && \
    yes | sdk install maven $MAVEN_VERSION && \
    yes | sdk install gradle && \ 
    rm -rf $HOME/.sdkman/archives/* && \
    rm -rf $HOME/.sdkman/tmp/*"

# serverless framework
RUN curl -o- -L https://slss.io/install | VERSION=2.29.0 bash

FROM builder 

WORKDIR $HOME/care-corner-api
RUN yarn
CMD [ "/bin/bash" ]



