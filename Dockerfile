FROM node:10

RUN apk update
RUN apk add --no-cache bash \
    apk add --no-cache openssl \
    apk add --no-cache curl \
    apk add --no-cache git

RUN addgroup -S care -g 1000 \
 && adduser -D care -G care -h /home/care -u 1000

USER care
RUN touch ~/.bash_profile
#RUN curl -sSLf https://get.volta.sh | bash

#RUN volta install node
#RUN volta install npm
#RUN volta install yarn
#RUN volta install serverless

CMD [ "/bin/bash" ]



