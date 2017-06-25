FROM debian:jessie

MAINTAINER Barata Mathieu



EXPOSE 8080 443 22



ADD tools .



#installation des apt 

RUN /bin/bash -c "source /task/kernel.sh" 


ENTRYPOINT "/task/roles/services/templates/starts_services.sh" && /bin/bash


#on fait un mount de html

VOLUME /var/www/html



WORKDIR /var/www/html
