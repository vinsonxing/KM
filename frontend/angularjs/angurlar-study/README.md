# chat-client

1.	Install nodejs
2.  Install python
3.  Install Visual Studio (optional)
4.	Set the proxy in command line by “set http_proxy=http://web-proxy.atl.hp.com:8080“  and “set https_proxy=http://web-proxy.atl.hp.com:8080“
5.	Run “npm install -g bower” to install bower
6.	Run “npm install && bower install” in the command line under the root folder, which contains the file ‘package.json’
7.  Run "npm install -g gulp”
8.  Run "npm install" under /mockServer
7.	Run “gulp” to start this server, which will open a browser


# Run UT

1. npm install & bower install to make sure all libs are loaded.
2. run "gulp test" to start the unit test
3. write your own UT under /test/spec based on Jasmine.
4. check the result in the 'gulp test' console.