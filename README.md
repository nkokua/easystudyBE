# 🛡️ Spring Boot Auth API

Spring Boot 기반으로 만든 인증/인가 API 프로젝트입니다.  
**회원가입**, **로그인**, **JWT(Json Web Token)** 기반 인증 시스템을 구현했습니다.

<br>

## 📌 기술 스택

- **Java 17**
- **Spring Boot 3.x**
- **Spring Security**
- **JWT (jjwt)**
- **JPA (Hibernate)**
- **Lombok**

<br>

## 🚀 기능 소개

### ✅ 회원가입 (Sign Up)
- 사용자는 `email`, `password`, `nickname`을 입력하여 회원가입할 수 있습니다.
- 비밀번호는 BCrypt로 해싱하여 저장됩니다.
- 중복 이메일 체크 포함.

### ✅ 로그인 (Sign In)
- 이메일과 비밀번호를 통해 로그인
- 로그인 성공 시 JWT 발급

### ✅ JWT 인증
- 로그인 후 요청 시 `Authorization: Bearer <token>` 헤더를 통해 인증
- Access Token의 유효성을 필터에서 검사
- 토큰이 유효하면 인증된 사용자 정보로 SecurityContext 설정

<br>

## 🗂️ API 명세 (예시)

### 📍 회원가입
