# 구독 서비스 예제
회원 관리 시스템에서 구독 서비스의 일부를 구현한 예제입니다.

## 개발 환경
Java 17 \
Spring Boot 3.3 \
MySQL 8.0

## 설정 요구사항
프로젝트 폴더에 있는 env.properties.sample 를 수정하여 env.properties 파일로 등록이 필요합니다.

```bash
cp env.properties.sample env.properties
vi env.properties # 환경 변수값 수정
```

## 파일 정보
* [docs/ddl.sql](docs/ddl.sql): subscription_service 스키마 생성, 관련 테이블 생성 (회원, 채널 및 구독 관련 테이블), 인덱스 생성 및 테스트용 초기 채널 데이터 등록 관련 DML 이 포함되어 있습니다.
* [docs/api_spec.md](docs/api_spec.md): 채널 구독/해지 및 이력 조회와 관련한 API 명세

## 기타 사항
* 연락처를 입력하여 구독 요청 시, 회원 테이블에 없는 경우 자동으로 회원 등록이 된다는 것으로 가정하여 개발 작업하였습니다.
* 추후 확장성을 위해 (다중화 환경에서의 동시성 이슈), DB 내 데이터 등록 및 변경 시 해당 플로우에 전체 트랙젠션을 설정하였으며, 각 요청이 Stateless 하도록 개발 진행하였습니다.