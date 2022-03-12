## 2022-03-08

#### 요구사항 분석
- 상품 도메인 모델
  - 상품 ID
  - 상품명
  - 가격
  - 수량
- 상품 관리 기능
  - 상품 목록
  - 상품 상세
  - 상품 등록
  - 상품 수정

## 2022-03-12

#### 프로젝트 생성
- start.spring.io
  - Project : `Gradle`
  - Language : `Java`
  - Spring Boot : `2.6.4`
  - Group : `hello`
  - Artifact : `item-service`
  - Package name : `hello.itemservice`  
\*\* 주의 할 점으로는 Packange name에는 특수 문자가 들어가지 않도록 조심
  - Dependencies : `Spring Web`, `Thymeleaf`, `Lombok`
- welcome page
  - `/resources/static/index.html`에 메인페이지 작성