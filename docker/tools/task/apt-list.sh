#!/bin/bash



tab=('apt-transport-https' 'ssh-client' 'build-essential' 'curl' 'ca-certificates' 'wget' 'apache' 'php' 'mysql' 'php-extension' 'services');

apt-get update -y

for task in ${tab[*]}
do
	source "$GLOBAL_PWD_TASK/roles/$task/main.sh"
done

