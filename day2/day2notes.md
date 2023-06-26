Spring Boot 101 - Day 2
Typically, it needs
- Class
- ClassConfig
- ClassController
- ClassRepository
- ClassService

Wherein "Class" here is your context

### Connecting to database
In order for IntelliJ and Docker to work together,  
ensure that the container and  the Spring Boot Application  
is running on localhost.

#### To run the docker command in local host, use the following
With password  
`docker run --name mysql-container -p 3306:3306 -e MYSQL_ROOT_PASSWORD= -d mysql`  
Without password  
`docker run --name mysql-container -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<password> -d mysql`

#### To access the mysql database using terminal (wsl), use the command
With password  
`docker exec -it mysql-container mysql -u root -p`  
Without password  
`docker exec -it mysql-container mysql -u root`

