#!/bin/bash 
function install-apt {
	printBash $1 "install"
	apt-get install -y --force-yes --no-install-recommends $1
}
