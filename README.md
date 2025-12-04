# Hotlap Server

## 프로젝트 개요

Hotlap Server는 헥사고날/클린 아키텍처 접근 방식을 따르는 Spring Boot 애플리케이션입니다. 다양한 도메인 엔티티를 관리하기 위한 RESTful API를 제공합니다.

## 아키텍처

### 헥사고날 아키텍처 (포트 및 어댑터)

이 프로젝트는 포트 및 어댑터라고도 알려진 헥사고날 아키텍처 패턴을 따릅니다. 주요 구성 요소는 다음과 같습니다:

- **도메인 계층**: 핵심 비즈니스 로직 및 엔티티 포함
- **애플리케이션 계층**: 도메인을 조율하는 유스케이스 포함
- **인바운드 어댑터**: 애플리케이션을 외부 세계에 노출하는 REST 컨트롤러
- **아웃바운드 어댑터**: 리포지토리 및 외부 서비스 클라이언트

### CQRS 패턴

이 프로젝트는 명령 쿼리 책임 분리(CQRS) 패턴을 구현합니다:

- **명령(Command)**: 쓰기 작업은 전용 명령 리포지토리 및 서비스에서 처리
- **쿼리(Query)**: 읽기 작업은 전용 쿼리 리포지토리 및 서비스에서 처리

## 패키지 구조

```
com.hotlap.server
├── application        # 애플리케이션 서비스 (유스케이스)
│   └── foo
│       ├── service        # 도메인 관련 비즈니스 로직
│       ├── model
│       │   ├── command    # 쓰기 작업을 위한 명령 객체
│       │   └── result     # 읽기 작업을 위한 결과 객체
│       ├── FooInquiryService.java
│       └── FooRegisterService.java
├── common             # 공통 유틸리티 및 공유 컴포넌트
│   └── exception      # 사용자 정의 예외 및 오류 처리
├── config             # 구성 클래스
│   ├── async          # 비동기 실행 구성
│   ├── jpa            # JPA 구성
│   ├── scheduler      # 스케줄러 구성
│   └── web            # 웹 및 Swagger 구성
├── domain             # 도메인 모델 및 비즈니스 로직
│   └── foo
│       ├── model      # 도메인 엔티티
│       └── repository # 도메인 리포지토리 (인터페이스)
├── inbound            # 인바운드 어댑터
│   ├── advice         # 예외 처리를 위한 컨트롤러 어드바이스
│   └── controller     # REST 컨트롤러
│       ├── common     # 공통 컨트롤러 컴포넌트
│       ├── foo        # Foo 도메인 컨트롤러
│       └── health     # 헬스 체크 컨트롤러
└── outbound           # 아웃바운드 어댑터
    └── mysql          # MySQL 영속성 어댑터
        ├── common     # 공통 영속성 컴포넌트
        └── foo        # Foo 영속성 구현
```

## 코딩 규칙

### 명명 규칙

- **패키지**: 소문자, 도메인 중심 이름 사용 (예: `foo`, `common`)
- **클래스**: PascalCase 사용 (예: `FooController`, `BaseEntity`)
- **메서드**: camelCase 사용 (예: `register()`, `findByIdOrThrow()`)
- **변수**: camelCase 사용 (예: `fooRepository`, `createdAt`)

### 디자인 패턴

- **빌더 패턴**: 복잡한 객체 생성에 사용 (예: `Foo.builder()`)
- **리포지토리 패턴**: 데이터 접근 추상화에 사용
- **CQRS 패턴**: 명령과 쿼리 책임의 분리
- **의존성 주입**: Spring의 생성자 주입과 `@RequiredArgsConstructor` 사용

### 코드 구성

- **도메인 엔티티**: `domain/<entity>/model`에 위치
- **리포지토리**: `domain/<entity>/repository`에 위치
- **애플리케이션 서비스**: `application/<entity>`에 위치
- **컨트롤러**: `inbound/controller/<entity>`에 위치
- **DTO**:
    - 명령(Commands): `application/<entity>/model/command`
    - 결과(Results): `application/<entity>/model/result`
    - 요청(Requests): `inbound/controller/<entity>/model/request`
    - 응답(Responses): `inbound/controller/<entity>/model/response`

## 구성

### 환경 프로필

애플리케이션은 여러 환경 프로필을 지원합니다:

- **dev**: 개발 환경
- **prod**: 프로덕션 환경

### 데이터베이스 구성

- MySQL을 데이터베이스로 사용
- 커넥션 풀링은 HikariCP로 관리
- JPA/Hibernate를 ORM으로 사용

### API 문서화

- API 문서화를 위해 Swagger/OpenAPI 구성
- 애플리케이션 실행 시 `/swagger-ui.html`에서 사용 가능

## 개발 워크플로우

### 시작하기

1. 저장소 복제
2. 로컬 MySQL 데이터베이스 구성 (application-dev.yml 참조)
3. dev 프로필로 애플리케이션 실행: `./gradlew bootRun --args='--spring.profiles.active=dev'`

### 새 도메인 엔티티 추가

1. `domain/<entity>/model`에 도메인 엔티티 생성
2. `domain/<entity>/repository`에 리포지토리 인터페이스 생성
3. `outbound/mysql/<entity>/repository`에 JPA 리포지토리 생성
4. `application/<entity>`에 애플리케이션 서비스 생성
5. 적절한 패키지에 DTO 생성
6. `inbound/controller/<entity>`에 컨트롤러 생성

### 테스팅

- 모든 컴포넌트에 대한 단위 테스트 작성
- 중요한 흐름에 대한 통합 테스트 작성
- 테스트에는 테스트 프로필 사용

### 오류 처리

- 사용자 정의 예외는 `BusinessException`을 상속해야 함
- 오류 코드는 `ErrorCode` enum에 정의
- 전역 예외 처리는 `GlobalExceptionHandler`에서 제공

## API 버전 관리

- API는 URL 경로에서 버전 관리 (예: `/api/v1/foo`)
- 주요 변경사항은 새 버전에서 도입

## 로깅

- Lombok의 `@Slf4j` 어노테이션과 함께 SLF4J 사용
- 중요한 애플리케이션 이벤트 및 오류 로깅
- application.yml에서 로그 레벨 구성
