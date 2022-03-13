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
- `File` -> `Setting` -> `Gradle` 에서 `IntelliJ IDEA`라고 적힌 부분을 `Gradle`로 변경

## 2022-03-13

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
  - 상품 삭제(개인이 만들어볼 것)
- 역할
  - 디자니어 : 요구사항에 맞ㅈ게 디자인하고, 디자인 결과물을 웹 퍼블리셔에게 넘겨준다
  - 웹 퍼블리셔 : 디자이너에게 받은 디자인을 기반으로 HTML, CSS를 만들어 개발자에게 제공한다
  - 백엔드 개발자 : 디자이너, 웹 퍼블리셔를 통해서 HTML 화면이 나오기 전까지 시스템을 설계하고, 핵심 비즈니스 모델을 개발한다. 이후 HTML이 나오면 이 HTML을 뷰 템블릿으로 변환해서 동적으로 화면을 그리고, 또 웹 화면의 흐름을 제어한다
- 참고
  - React, Vue.js 같은 웹 클라이언트 기술을 사용하고, 웹 프론트엔드 개발자가 별도로 있으면, 웹 프론트엔드 개발자가 웹 퍼블리셔 역할까지 포함해서 하는 경우도 있다
  - 웹 클라이언트 기술을 사용하면, 웹 프론트엔드 개발자가 HTML을 동적으로 만드는 역할과 웹 화면의 흐름을 담당한다. 이 경우 백엔드 개발자는 HTML 뷰 템플릿을 직접 만지는 대신에, HTTP API를 통해 웹 클라이언트가 필요로 하는 데이터와 기능을 제공하면 된다

#### 상품 도메인 개발
- `Item` 클래스에서 `Integer`를 쓰는 이유는 상품에 가격이나 수량이 null로 들어갈 수 있을 수도 있음
- `@Data`는 핵심 도메인 모델 상황에서는 위험할 수 있으니 사용 자제
- 단순 DTO 일떄는 `@Data`를 사용해도 되지만 그래도 주의해야함
- 실무에서는 `HashMap`과 `long`보다는 `AtomicLong`같은 것을 사용한다

#### 상품 서비스 HTML
- `bootstrap`는 익혀두자
  - `resources/static/css`에 넣는다
  - 혹시 실행을 했는데 작동이 안된다면 url로 해당 경로를 호출해보고 없다고 뜬다면 `out` 폴더를 삭제 해보자
- 정적 리소스가 공개되는 `/resources`static` 폴더에 HTML을 넣어두면, 실제 서비스에서도 공개된다. 서비스를 운영한다면 지금처럼 공개할 필요없는 HTML을 두는 것은 주의하자

#### 상품 목록 - 타임리프
- URL 링크 표현식 - `@{...}`
- 리터럴 대체 - `|...|`
- 반복 출력 - th:each
- 변수 표현식 - `${...}`
- 내용 변경 - `th:text`

#### 상품 상세