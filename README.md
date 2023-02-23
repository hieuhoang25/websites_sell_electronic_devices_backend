# docker-compose
# Let's use below single docker-compose command to run the whole setup:
    docker-compose up
# Let's use the below docker-compose command to start and run all the containers in the background:
    docker-compose up -d
# Use the below docker command to check the list of currently running containers:
    docker ps
# Use the below docker command to list all the docker images:
    docker images ls

# Dockerfile
    docker build -t api-springboot-service . 
    docker run -d -ti -p 8080:8080 --name api-springboot-service  api-springboot-service