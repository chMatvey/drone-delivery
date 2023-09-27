FROM openjdk:21 as build
WORKDIR /workspace/app

COPY . .

RUN microdnf install findutils
RUN ./gradlew bootJar

FROM openjdk:21

COPY --from=build workspace/app/build/libs/*.jar app.jar

ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]