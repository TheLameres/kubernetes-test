FROM bellsoft/liberica-openjdk-alpine:11

COPY maven/target/kubernetes-test-0.0.1-SNAPSHOT.jar /deployments/app.jar
# Copying a file inside project root directory
EXPOSE 8080
CMD ["java", "-jar", "/deployments/app.jar"]