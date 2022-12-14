# MeatApplication

## 사용 기술 스택

### 프로젝트 구성
- MVVM 프레임워크
- Android Clean Architecture
### 의존성 주입
- Hilt
### 네트워크
- Retrofit2
### 비동기 처리
- Kotlin Coroutines
### 이미지 리소스
- Coil ( Compose ImageView 사용을 위하여 사용 )
### 뷰 구성
- Jetpack Compose
- Google Accompanist ( TabLayout Compose 로 사용하기 위해 사용 )

## 프로젝트 설치 및 빌드 방법
> Android Studio 가 설치되어 있지 않다면 설치 부탁드립니다.
### 코드 확인 및 실행 시
1. 전송한 .zip 파일 압축 풀기 ( 혹은 코드 받아서 사용 )
2. Android Studio 실행
3. Open 후 압축 풀기 된 폴더의 루트 위치 열기
4. Gradle Sync 후 Run 버튼 클릭 ( 에뮬레이터 혹은 실 기기 와 연결되어 있어야 함. )

## 프로젝트 사용법 및 기능
### 식품 리스트 페이지
1. 처음 입장 시 로딩 프로그레스가 나오게 됨
2. 모든 UiModel 변환이 완료되면, 로딩 프로그레스가 해제되면서, 완료된 아이템이 나오게 됨.
3. 식품의 좋아요 버튼을 누르게 되면, 로컬 데이터베이스에 저장되게 됨.

### 식품 찜 목록 페이지
1. 탭 변경시 빈 입력창과 함께, 현재 데이터베이스에 들어가있는 아이템이 Flow 형식으로 보여지게 됨.
2. 아이템의 좋아요 버튼을 누르게 되면, 로컬 데이터베이스에서 사라지면서, 아이템이 보이지 않게 됨
3. 입력창 텍스트 입력시, 텍스트가 입력됨에 따라서 필터링이 진행됨.

