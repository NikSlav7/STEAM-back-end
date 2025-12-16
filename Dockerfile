FROM eclipse-temurin:17-jdk
RUN mkdir -p /home/app
COPY /target/steam-backend.jar /home/app
CMD ["java", "-jar", "/home/app/steam-backend.jar"]