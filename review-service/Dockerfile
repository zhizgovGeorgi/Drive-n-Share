#
# Use an official Maven image as a parent image
FROM maven:3.8.4-openjdk-17-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the working directory
COPY ./pom.xml .

# Copy the rest of the application code
COPY ./src ./src

# Build the Maven project
RUN mvn clean install -DskipTests

# Debug: List contents of target directory
RUN ls -la ./target

# Use another lightweight image as a base image
FROM openjdk:17-jdk-slim AS runtime

# Set the working directory in the container
WORKDIR /app

# Copy the built artifact from the build stage to the runtime stage
COPY --from=build /app/target/*.jar ./app.jar

# Specify the command to run your application
CMD ["java", "-jar", "app.jar"]
#FROM azul/zulu-openjdk-alpine:17
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]