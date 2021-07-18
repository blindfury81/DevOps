#!/bin/bash

##  Obtaint he input paramaters.  ##
serverEnv="$1"
softwareSelection="$2"

#########################################################
##  End if not running within the relative directory.  ##
if  [ ! -f serverSetup.sh ]
then
  echo "ERROR | This script must be executed from within it's relative path.."
  exit 1
fi

##  Inherit global functions.  ##
. ../shell/funcs.sh

##  Exit if a server environment is not provided.  ##
if  [ "${serverEnv}" = "" ]
then
  errorMsg "A valid server environment must be provided as input paramater #1."
fi

##  Exit if a software product was not provided.  ##
if  [ "${softwareSelection}" = "" ]
then
  errorMsg "A valid software product must be provided as input paramater #2."
fi

##  Exit if the inventory file is not found.  ##
if  [ ! -f inventory_files/${serverEnv}.ini ]
then
  errorMsg "The required inventory file inventory_files/${serverEnv}.ini is not present."
fi

##  Exit if the group_vars file is not found.  ##
if  [ ! -f group_vars/${serverEnv}_${softwareSelection}.yml ]
then
  errorMsg "The required variables file group_vars/${serverEnv}_${softwareSelection}.yml is not present."
fi

##  Obtain operator SSH password if not already present.  ##
if  [ "${sshPassword}"  ==  "" ]
then
  echo -n "Enter SSH password: "
  stty -echo
  read  sshPass
  sshPassword="${sshPass}"
  stty echo
fi

##  Add the hosts to known_hosts.  ##
hostsList=`cat  inventory_files/${serverEnv}..ini | sed -n '/\[${softwareSelection}\]/,$p' | sed 1d | sed -n '/\[.*/q;p' | awk '{print $1;}' | sort -u`
for singleHost in ${hostsList}
do
  infoMsg "Adding ${singleHost} to ${HOME}/.ssh/known_hosts"
  ssh-keygen -R ${singleHost}
  ssh-keyscan -H ${singleHost} >> ~/.ssh/known_hosts
done

infoMsg "Executing the systemdRestart.yml playbook."
ansible-playbook  systemdRestart.yml -i inventory_files/${serverEnv}.ini  --forks=1  \
  --extra-vars serverEnv=${serverEnv}  \
  --extra-vars softwareSelection=${softwareSelection}  \
  --extra-vars ansible_become_pass=${SSHPASS}  \
  --extra-vars ansible_ssh_pass=${SSHPASS}

infoMsg "Playbook execution complete."

rm -f *.retry

exit 0