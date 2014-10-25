# -*- mode: ruby -*-
# vi: set ft=ruby :

$cleanup = <<SCRIPT
# Stop and remove any existing containers
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker rmi geolib

SCRIPT

# Vagrantfile API/syntax version. Don't touch unless you know what you're doing!
VAGRANTFILE_API_VERSION = "2"

Vagrant.configure(VAGRANTFILE_API_VERSION) do |config|
  config.vm.box = "parallels/ubuntu-14.04"
  # Port forwarding not working with parallels provider yet
  #config.vm.network "forwarded_port", guest: 5984, host: 5984
  config.vm.network "private_network", ip: "192.168.33.33"

  config.vm.provision "shell", inline: $cleanup

  config.vm.provision "docker" do |d|
      #d.pull_images "shykes/couchdb"
      d.build_image "/vagrant/",
        args: "-t geolib"
      d.run "shykes/couchdb",
        args: "-p 5984:5984 shykes/couchdb /bin/sh -e /usr/bin/couchdb -a /etc/couchdb/default.ini -a /etc/couchdb/local.ini -b -r 5 -p /var/run/couchdb/couchdb.pid -o /dev/null -e /dev/null -R"
      d.run "geolib",
        args: "-p 8080:8080 -e GEOLIB_MOCK=true"
  end

  config.vm.provision "shell",
      #inline: "while ! nc -q 1 localhost 5984 </dev/null; do sleep 1; done"
      # TODO: remove workaround for giving couchdb enough time to start up
      inline: "sleep 1"
end
