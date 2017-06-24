#!/bin/bash
 


echo "alias starts_services='sh -c /task/roles/services/templates/starts_services.sh'" >> ~/.bashrc

rm /etc/rc.local

cp /task/roles/services/templates/rc.local /etc/rc.local
