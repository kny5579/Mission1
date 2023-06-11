# Mission 1
내 위치 기반 공공 와이파이 정보를 제공하는 웹 서비스 개발
<br>
<br>

## 🖥️ 프로젝트 소개
서울시 오픈 API(공공와이파이 서비스) 정보를 가져와서 입력한 위치를 기반으로 db에 저장된 다양한 기능 수행
<br>
<br>

## 🕰️ 개발 기간
* 22.05.22일 - 23.06.11일
<br>
<br>

## ⚙️ 개발 환경 및 라이브러리
- `JDK 1.8.0`
- **IDE** : Eclipse
- **Tomcat** : v8.5
- **Database** : Maria DB
- **OS**: Window
- gson
- okhttp3
<br>
<br>

## 💻 기술 스택
* 백엔드
  * JAVA, JSP
* 프론트엔드
  * HTML, Javascript
* 데이터베이스
  * Maria DB
<br>
<br>

## 📌 주요 기능
#### - 오픈 api 와이파이 정보 가져오기
![image](https://github.com/kny5579/Mission1/assets/95288763/6c51362e-f8f9-4866-acb6-bd49e16ba02c)

- open api가져오기 버튼 클릭시 db에 insert 후 문구 출력
- okhttp3와 gson을 통해 서울시 공공 데이터 파싱 후 db에 insert
- batch 메소드를 통해 insert 시간 1분대-> 5초 미만으로 감소
<br>
<br>

#### 내 위치 입력 후 근처 WIFI정보 보기
![image](https://github.com/kny5579/Mission1/assets/95288763/431f65f2-bc4c-478e-acaa-cf1f121141b6)

- (현재 제주도에 있는 관계로 거리가 상당히 멀게 나옴)
- 버튼 클릭시 내 위치 자동 입력 기능
- 근처 와이파이 버튼 클릭시 와이파이 정보가 가까운 순서로 20개 출력 기능
<br>
<br>

#### 상세정보 보기
![image](https://github.com/kny5579/Mission1/assets/95288763/32cd502c-8633-4c6b-8810-efb749da8eba)

- 와이파이명 클릭 시 상세정보 보기 페이지로 이동
- 북마크 그룹 선택 및 북마크 추가 기능
- 북마크 그룹 이름 선택 시 none값을 선택하면 북마크 추가가 안되도록 수정
<br>
<br>

#### 위치 히스토리 목록
![image](https://github.com/kny5579/Mission1/assets/95288763/b2d12382-3fa1-478f-adf4-13b9dc719999)

- 근처 와이파이 보기 버튼 클릭시 위치 정보와 조회일자를 히스토리db에 저장하는 기능
- 히스토리 삭제 기능
<br>
<br>

#### 북마크 그룹 관리
![image](https://github.com/kny5579/Mission1/assets/95288763/c7df564a-4c90-49b4-bbcf-3f06e47e2d26)
![image](https://github.com/kny5579/Mission1/assets/95288763/46f9edcd-f830-493b-9f8c-4f6c9dbedf9f)
![image](https://github.com/kny5579/Mission1/assets/95288763/7db4463c-de18-4c88-a4ba-df5cc0661ec4)
![image](https://github.com/kny5579/Mission1/assets/95288763/9a0c6152-b642-42f2-8778-c8be4b87d007)
![image](https://github.com/kny5579/Mission1/assets/95288763/562026d0-ed3f-44a8-bc54-1b33aed37ea4)

- 북마크 그룹 작성, 읽기, 수정, 삭제(CRUD) 기능
- 한 북마크 그룹 안에 여러 북마크들이 저장될 수 있는 기능
- 북마크 순서에 따른 정렬
- 북마크 그룹 삭제 시 북마크 이름과 순서를 수정할 수 없도록 설정
<br>
<br>

#### 북마크 보기
![image](https://github.com/kny5579/Mission1/assets/95288763/c555156a-2b15-44ae-9fb5-994aa43ecd53)
![image](https://github.com/kny5579/Mission1/assets/95288763/33ace34f-dc81-40e5-a78b-604806a6ca91)

- 상세정보 페이지에서 북마크 이름 선택 및 북마크 추가를 했을 경우 북마크 보기 페이지에서 확인
- 와이파이명 클릭시 상세정보 페이지로 다시 넘어가서 상세 정보 확인 기능
- 북마크 그룹과 join을 통해 북마크 그룹 수정 시 이에 속한 북마크 리스트의 데이터도 수정
- 북마크 그룹과 join을 통해 북마크 그룹 삭제 시 이에 속한 북마크 리스트의 데이터도 삭제
- 북마크 삭제 기능
