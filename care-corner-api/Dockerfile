FROM node:current-buster as builder

ARG DEBIAN_FRONTEND=noninteractive
RUN apt-get update && \
    apt-get install -y \
    python-dev \
    python-pip \
    zip \
    unzip && \
    rm -rf /var/lib/apt/lists/*

# awscli and awslocal
RUN pip install awscli-local[ver1]

# sdkman; java & maven
ARG JAVA_VERSION="11.0.10.j9-adpt"
ARG MAVEN_VERSION="3.6.3"
RUN curl -o- -L https://get.sdkman.io | bash
RUN bash -c "source $HOME/.sdkman/bin/sdkman-init.sh && \
    yes | sdk install java $JAVA_VERSION && \
    yes | sdk install maven $MAVEN_VERSION && \
    rm -rf $HOME/.sdkman/archives/* && \
    rm -rf $HOME/.sdkman/tmp/*"

# serverless framework
RUN curl -o- -L https://slss.io/install | VERSION=2.29.0 bash

CMD [ "/bin/bash" ]



