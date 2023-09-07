# Android_Team119_Project

# 해당 리드미는 개발 중 임시로 작성 중 입니다. 추후 변경될 수 있습니다.

## 목차 -> 추후 변경 가능
1. 프로젝트 명
2. 프로젝트 설명
3. 개발 기간
4. 주요 기능
5. 팀원 및 역할 분담
6. 프로젝트 설치 및 실행 방법
7. 프로젝트 사용 방법
8. 참고 자료 / 외부 리소스 정보
9. 팀 노션 주소
10. 라이센스

## 프로젝트 명
재난 연락 119

## 프로젝트 설명
통화 연동 기능 / 연락처 추가 / 연락처 상세보기 기능을 가진 전화번호부 Application을 개발하고자 한다.

## ⏰ 개발 기간
23.09.04 ~ 23.09.11
23.09.11 14:00 발표일

## ⚙️ 주요 기능
- 메인 페이지
  - Fragment를 이용하여 리스트 페이지와 디테일 페이지 이동
  - 툴바에 연락처 추가 버튼을 누르면 다이얼로그로 이동
  - 리스트 보기/그리드 보기 기능
- 리스트 페이지
  - 저장된 연락처리스트 보기
  - 연락처를 누르면 디테일 페이지로 이동
- 디테일 페이지
  - 연락처 세부사항 표시
  - Notification으로 알림 기능
  - call 버튼으로 저장된 연락처로 전화연결
  - 연락처 추가 버튼으로 휴대폰에 저장된 연락처 추가
- 마이 페이지
  - 디테일 페이지를 활용하여 내 정보 보여주기
- 다이얼로그
  - 연락처 추가 기능
  - 입력타입 제한

## 🎉 팀원 및 역할 분담
<table>
  <tbody>
    <tr>
      <td align="center"><a href="https://github.com/LeeChoongHwan"><img src="https://avatars.githubusercontent.com/u/102038187?v=4" width="100px;"><br /><sub><b>FE 팀장 : 이충환</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/AJH1346"><img src="https://avatars.githubusercontent.com/u/139087984?v=4" width="100px;"><br /><sub><b>FE 팀원 : 안주환</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/LeeDonghee0917"><img src="https://avatars.githubusercontent.com/u/86705733?v=4" width="100px;"><br /><sub><b>FE 팀원 : 이동희</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/Seulbi-Lee-project"><img src="https://avatars.githubusercontent.com/u/138438363?v=4" width="100px;"><br /><sub><b>FE 팀원 : 이슬비</b></sub></a><br /></a></td>
      <td align="center"><a href="https://github.com/ckh124"><img src="https://avatars.githubusercontent.com/u/113021323?v=4" width="100px;"><br /><sub><b>FE 팀원 : 조광희</b></sub></a><br /></a></td>
     <tr/>
  </tbody>
</table>

1. 이충환
   - Singleton 활용 Data 관련 Class 구현
   - Url / Bitmap 기능 구현
   - Adapter 기능 구현
   - Swipe 통화 연결 기능 구현
   - Notification 기능 구현
   - 발표
2. 안주환
   - RecyclerView 활용 전화번호부 리스트 구현
   - 연락처 리스트 UI 구현
   - Fragment 연결
   - PR 및 issue 관리
3. 이동희
   - Viewpager / Tablayout 활용 Main Activity layout 구현
   - Main Activity 기능 구현
   - 리스트/그리드 UI 구현
   - 리스트/그리드 보기 기능 구현
   - 플로팅 버튼 기능 구현
   - 앱 전체 디자인
4. 이슬비
   - 팀원 상세보기 layout 구현
   - call 버튼으로 전화연결 기능 구현
   - 연락처 추가 버튼으로 휴대폰에 저장된 연락처 추가 기능 구현
   - 리드미 작성
   - ppt 자료 준비
5. 조광희
   - 연락처 추가하기 Dialog UI 구현
   - Dialog 기능 구현
   - notify 기능 구현
   - 리스트 삭제 기능 구현
   - 좋아요 버튼 기능 구현
   - 트러블 슈팅 작성

## ♻️ 참고 자료 / 외부 리소스 정보
1. Viewpaper2 / TabLayout
   - [카레유 - 여러개의 Fragment Swipe 구현 방법: ViewPager2](https://curryyou.tistory.com/415)
   - [카레유 - TabLayout와 ViewPager2연결 구현 방법](https://curryyou.tistory.com/416)

## 📖 팀 노션
https://www.notion.so/19-Team-119-c428f3334167461d9baff625f1081f1c

## 기술스택
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/Kotlin-7F52FF?style=for-the-badge&logo=Kotlin&logoColor=white"><img src="https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=Android&logoColor=white">
