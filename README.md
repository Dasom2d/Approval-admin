# Approval-admin

## 프로젝트 소개

#### Lion Company의 직원들이 결재 문서를 조회하고 상신할 수 있다.
|멤버ID|이름|직급|
|:-:|:-:|:-:|
|1|라이언|회장|
|2|튜브|과장|
|3|네오|대리|
|4|무지|사원|


### 시스템 구성환경
- Java, Springboot
- Vue.js, Javascript(ES6)
- Gradle
- MySQL


### 실행 방법
#### 프로젝트 빌드
1. project root 이동
2. `./grablew build`  생성된 jar 확인
생성된 jar 예시) `kakaoPay-0.0.1-SNAPSHOT.jar`

#### 프로젝트 실행
1. `java -jar hello-spring-0.0.1-SNAPSHOT.jar` 
2. `http://localhost:3000/` 에서 프로젝트 확인 가능


### 문제 해결 전략
#### 1. 로그인
```
- 로그인 페이지에서 직원을 선택하여 결재 웹사이트에 접속할 수 있다.
```

#### 2. 메인 
```
- 본인이 요청한 결재 문서 목록을 확인할 수 있다.
- 본인이 승인자로 지정된 결재 문서 목록을 확인할 수 있다.
```

#### 3. 등록
```
- 승인자를 지정하여 결재를 요청할 수 있다.
```

#### 4. 상세 
```
- 결재 요청은 사원 -> 대리 -> 과장 -> 회장 순으로 가능하다.
- 회장은 결재를 올릴 필요가 없다.
```

##### 요청자
```
- 본인이 상신한 결재 문서의 내용을 확인할 수 있다.
- 요청 상태일 경우, 결재 문서의 내용을 수정하거나 승인자를 변경할 수 있다.
- 요청 상태일 경우, 결재 문서를 삭제 할 수 있다.
```

##### 승인자
```
- 본인에게 요청온 문서를 승인하거나 반려할 수 있다.
```

