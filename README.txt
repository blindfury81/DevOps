Hello, you are reviewing the initial release of my DevOps repository; as such the documentation is in need of some work.

My intent of this initial release is to provide a set of ansible scripts that simplify the download, installation, and configuration of open-source software.

Please review the contents of the ansible/inventory_files and ansible/group_vars files to get a feel for the scripts.  By completing the required information in these files you should be able to install and configure software across clusters.

Initially, you must place your id_rsa.pub key in security/ssh <--  This is necessary as the scripts add the public key to the destination servers/application owner accounts.

By running the following:
  (cd ansible; ./serverSetup.sh "dev" "kafka")
You will be able to deploy the kafka software based upon the servers you've listed in the corresponding inventory file (named dev.yml) and group_vars file (named dev_kafka.yml).  Notice that the file names and input paramaters are correlated to one another.

The scripts will unpack and configure the software based upon settings within the group_vars/dev_kafka.yml file along with the firewalld and systemd settings.

More documentation to come.  For now it may be necessary to review the scripts to understand fully what I have done.