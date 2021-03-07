FROM alpine:latest

RUN apk update
RUN apk add --no-cache bash
RUN apk add --no-cache openssl
RUN apk add --no-cache curl
RUN apk add --no-cache git

RUN addgroup -S care \
 && adduser -S care -G care

USER care
RUN touch ~/.bash_profile
RUN curl -sSLf https://get.volta.sh | bash

RUN volta install node
RUN volta install npm
RUN volta install yarn
RUN volta install serverless

CMD [ "/bin/bash" ]



