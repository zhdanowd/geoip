1. Clone the project : git clone https://github.com/zhdanowd/geoip;
2. Download IPV4 CSV from https://lite.ip2location.com/database/ip-country-region-city-latitude-longitude and put IP2LOCATION-LITE-DB5.CSV file in the project root directory;
3. Set your MySql username/password to root/root;
4. Enter MySql server from the project root directory : mysql -u root -p root;
5. From the MySql server run this command: source db_setup.sql
6. Exit MySql;
7. From the project root directory run:  mvn package;
8. Go to the target directory and run java -jar geoip-1.0-SNAPSHOT.jar
9. In browser enter http://localhost:8080/geoip/{ip}, instead of ip enter any valid IPv4 address
10. Press enter and get geo information about this ip address.
