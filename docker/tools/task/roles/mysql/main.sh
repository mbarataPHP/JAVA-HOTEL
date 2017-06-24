#!/bin/bash

debconf-set-selections <<< 'mysql-server mysql-server/root_password password docker'
debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password docker'
install-apt mysql-server
install-apt mysql-client

service mysql start

mysql -uroot -pdocker < "$GLOBAL_PWD_TASK/roles/mysql/templates/hotel_structure.sql"

mysql -uroot -pdocker < "$GLOBAL_PWD_TASK/roles/mysql/templates/hotel.sql"
