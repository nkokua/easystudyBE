# 1단계: 빌드 환경 (Builder Stage)
# 애플리케이션을 빌드하는 데 필요한 모든 것을 포함합니다.
# 여기서는 Java 17을 사용합니다. 필요한 Java 버전에 맞게 변경하세요.
FROM eclipse-temurin:17 AS builder

# 컨테이너 내에서 작업할 디렉토리를 설정합니다.
WORKDIR /app

# Gradle Wrapper 관련 파일들을 복사합니다.
# 이는 의존성 다운로드 및 빌드 시스템을 Docker 이미지 안에 포함하기 위함입니다.
COPY gradlew .
COPY gradle gradle
RUN chmod +x gradlew

#gradlew 실행권한 부여
COPY build.gradle .
COPY settings.gradle .

# Gradle 캐싱을 위해 필요한 의존성만 먼저 다운로드합니다.
# 이렇게 하면 소스 코드가 변경되어도 이 단계는 재사용되어 빌드 속도가 빨라집니다.
RUN ./gradlew dependencies --no-daemon
COPY . .
RUN ./gradlew --no-daemon clean build


# 2단계: 실행 환경 (Runner Stage)
# 빌드된 JAR 파일만 포함하여 애플리케이션을 실행하기 위한 최소한의 환경을 구축합니다.
# JDK 대신 더 가벼운 JRE 이미지를 사용합니다.
FROM eclipse-temurin:17-jdk-jammy
# 볼륨이 있다면, 볼륨이 마운트될 디렉토리를 생성할 수 있습니다.
# RUN mkdir /logs # 예: 로그 파일이 저장될 디렉토리

# 빌더 스테이지에서 생성된 JAR 파일을 가져옵니다.
# 'ARG JAR_FILE'은 빌드 인자이며, 'app/build/libs/*.jar'는 Gradle 빌드 결과물의 경로입니다.
ARG JAR_FILE=/app/build/libs/*.jar
COPY --from=builder ${JAR_FILE} app.jar

# Spring Boot 애플리케이션이 외부에서 접근할 포트를 노출합니다.
# 기본적으로 Spring Boot는 8080 포트를 사용합니다.
EXPOSE 8080

# 컨테이너가 시작될 때 실행될 명령어입니다.
# -jar app.jar를 사용하여 빌드된 JAR 파일을 실행합니다.
ENTRYPOINT ["/bin/sh", "-c", "echo 'Waiting for PostgreSQL...' && while ! nc -z db 5432; do sleep 1; done; echo 'PostgreSQL started, launching application...'; java -jar app.jar"]
