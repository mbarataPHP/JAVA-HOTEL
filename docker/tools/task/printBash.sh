#!/bin/bash 

function printBash {

	if [ -N "$2" ]; then
		typeColor=$2
	else
		typeColor="normal"
	fi

	# Escape code
	esc=`echo -en "\033"`

	# Set colors
	cc_red="${esc}[0;31m"
	cc_green="${esc}[0;32m"
	cc_yellow="${esc}[0;33m"
	cc_blue="${esc}[0;34m"
	cc_normal=`echo -en "${esc}[m\017"`

	if [ "install" == $typeColor ]; then

		echo "${cc_yellow} $1 ${cc_normal}"

	elif [ "normal" == $typeColor ]; then

		echo "${cc_normal} $1 ${cc_normal}"

	else

		echo "${cc_normal} $1 ${cc_normal}"

	fi

 
}
