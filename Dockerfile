# TODO 베이스 이미지 private ECR 이미지로 변경
FROM amazoncorretto:17-al2023-headless
WORKDIR application
ARG EXTRACTED=build/libs
COPY ${EXTRACTED}/dependencies/ ./
COPY ${EXTRACTED}/spring-boot-loader/ ./
COPY ${EXTRACTED}/snapshot-dependencies/ ./
COPY ${EXTRACTED}/application/ ./
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
