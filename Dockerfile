# 1단계: 빌드 환경 (Builder Stage)
# 애플리케이션을 빌드하는 데 필요한 모든 것을 포함합니다.
# 여기서는 Java 17을 사용합니다. 필요한 Java 버전에 맞게 변경하세요.
FROM eclipse-temurin:17 AS builder

# 컨테이너 내에서 작업할 디렉토리를 설정합니다.
WORKDIR /app

COPY ./EasyStudy.jar app.jarg

ENTRYPOINT ["java","-jar","app.jar"]