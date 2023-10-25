# CMS

## 기술 스택
- Java 17
- Gradle
- Spring Boot 3.1.X
  - Web
  - Security
  - JPA

## 실행 방법
```bash
cd ${PROJECT_DIR}
./gradlew bootJar
java -jar build/lib/application.jar
```

## 이미지 빌드 방법
```bash
cd ${PROJECT_DIR}
./gradlew bootJar
java -Djarmode=layertools -jar build/libs/application.jar extract --destination build/libs
DATE_TIME=$(TZ=Asia/Seoul date "+%y%m%d.%H%M")
COMMIT_HASH=$(git rev-parse HEAD)
IMAGE=cms:${DATE_TIME}-${COMMIT_HASH:0:4}
docker build -t ${IMAGE} .
```
