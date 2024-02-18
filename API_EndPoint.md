## 로그인

- POST `/auth/login`

```json
// Request
{
"userId": "사용자 아이디",
"password": "사용자 비밀번호"
}
```

```json
// Response - 정상적으로 로그인 된 경우
{"status": 200, "data": "사용자 아이디"}
// Response - 존재하지 않은 계정이나 비밀번호가 틀린 경우
{"status": 401, "data": "존재하지 않는 계정이거나 비밀번호가 잘못 되었습니다."}
```

## 회원가입

- POST `/auth/register`

```json
// Request
{
"name": "실명",
"age": 18,
"school": "부산컴퓨터과학고등학교",
"license": ["컴퓨터활용능력 1급", "ITQ 엑셀"],
"userId": "사용자 아이디",
"password": "사용자 비밀번호"
}
```

```json
// Response - 정상적으로 회원가입 된 경우
{"status": 200, "data": "사용자 아이디"}
// Response - 이미 가입 된 계정일 경우
{"status": 403, "data": "이미 존재하는 아이디입니다."}
// Response - 값이 누락 되었거나 값이 잘못된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
```

## 직업 & 목표 AI TodoList

### 직업 또는 목표 등록

- POST `/api/todolist/create`

```json
// Request
{
"goal": "목표",
"period": 3, // 3, 6, 9, 12개월 중 숫자만 전송
"todos": 10 // 기간 안에 해결 할 Todos 개수
}
```

```json
// Response - 정상적으로 등록 된 경우
{"status": 200, "data": "정상적으로 목표가 설정되었습니다."}
// Response - 로그인이 되지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}
// Response - 목표가 비어 있거나 종료 기간이 설정되지 않은 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
// Response - 목표가 이미 등록되어 있는 경우
{"status": 409, "data": "이미 목표가 등록되어 있습니다."}
```

### 목표 가져오기

- GET `/api/todolist`

```json
// Response - 정상적으로 불러온 경우
{
    "status": 200,
    "data": {
        "id": 2,
        "userId": "사용자 아이디",
        "goal": "목표",
        "endDate": "2024-05-18",
        "todos": [
            {
                "id": 1,
                "todo": "테스트 값 1",
                "completed": true
            },
            {
                "id": 2,
                "todo": "테스트 값 2",
                "completed": false
            },
            {
                "id": 3,
                "todo": "테스트 값 3",
                "completed": false
            }
        ]
    }
}

// Response - 설정된 목표가 없는 경우
{"status": 400, "data": "설정된 목표가 없습니다."}
```

### 목표(Todo) 완료 혹은 미완료 처리

- POST `/api/todolist/updateCompleted`

```json
// Request - 완료 처리
{
"todoId": 1,
"completed": true
}

// Request - 미완료 처리
{
"todoId": 1,
"completed": false
}
```

```json
// Response - 정상적으로 완료 처리 된 경우
{"status": 200, "data": "정상적으로 완료 처리 되었습니다."}

// Response - 정상적으로 미완료 처리 된 경우
{"status": 200, "data": "정상적으로 미완료 처리 되었습니다."}

// Response - 로그인이 되지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}

// Response - 값이 비어 있거나 잘못된 값이 입력된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
```

### 목표 삭제 처리(취소 처리)

- POST `/api/todolist/cancel`

```json
// Request - 목표 삭제 처리는 필요한 body 데이터가 없음
```

```json
// Response - 정상적으로 취소 처리 된 경우
{"status": 200, "data": "정상적으로 취소 처리 되었습니다."}

// Response - 로그인이 되지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}

// Response - 등록된 TodoList가 없는 경우
{"status": 400, "data": "등록된 TodoList가 없습니다."}

// Response - 잘못된 값이 입력된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
```

### 어려울 때 도움을 받을 수 있는 챗봇

- POST `/api/todolist/chatbot`

```json
// Request
{
"message": "이러한걸 하고 있는데 문제가 있으니 도움을 줘"
}
```

```json
// Response - 정상적으로 요청이 온 경우
{"status": 200, "data": "AI 답변"}

// Response - 로그인이 되지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}

// Response - 잘못된 값이 입력된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}

// Response - AI가 정상적으로 답변을 생성하지 못한 경우
{"status": 500, "data": "AI가 답변을 생성하는데 문제가 발생하였습니다. 다시 한번 질문해주세요."}
```

## 커뮤니티

### 최근에 등록 된 게시글 불러오기

- GET `/api/posts`

```json
// Response - 정상적으로 요청이 온 경우
{
    "status": 200,
    "data": [
        {
            "id": 2,
            "title": "테스트 글",
            "preview": "이러쿵저러쿵...",
            "userId": "사용자 아이디"
        },
        {
            "id": 1,
            "title": "테스트 글",
            "preview": "이러쿵저러쿵...",
            "userId": "사용자 아이디"
        }
    ]
}
```

### 게시글 작성하기

- POST `/api/posts/create`

```json
// Request
{
"title": "글 제목",
"content": "글 내용",
}
```

```json
// Response - 정상적으로 글이 등록된 경우
{"status": 200, "data": "정상적으로 글이 등록되었습니다."}

// Response - 로그인이 되어 있지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}

// Response - 잘못된 값이 입력된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
```

### 게시글 보기

- GET `/api/posts/{PostId}`

```json
// Response - 게시글이 있는 경우
{
    "status": 200,
    "data": {
        "userId": "사용자 아이디",
        "postId": 1,
        "title": "테스트 글",
        "content": "이러쿵저러쿵",
        "comments": [
            {
                "id": 1,
                "userId": "사용자 아이디",
                "content": "테스트 댓글"
            },
            {
                "id": 2,
                "userId": "사용자 아이디",
                "content": "테스트 댓글"
            }
        ]
    }
}
```

### 게시글 삭제

- DELETE `/api/posts/delete/{postId}`

```json
// Request - URL에서 데이터를 넘기면 됩니다.
```

```json
// Response - 정상적으로 삭제 된 경우
{"status": 200, "data": "정상적으로 게시글이 삭제되었습니다."}

// Response - 게시글 작성자가 사용자 아이디와 다른 경우
{"status": 403, "data": "해당 게시글을 작성한 계정으로만 게시글 삭제가 가능합니다."}

// Response - 로그인이 되어 있지 않은 경우
{"status": 403, "data": "로그인 후 이용하시기 바랍니다."}

// Response - 잘못된 값이 입력된 경우
{"status": 400, "data": "값이 누락되었거나 잘못된 값이 있습니다."}
```

### 댓글 등록하기

- POST `/api/comments/create`

```json
// Request
{
"postId": 1, // 게시글 ID
"content": "댓글 내용"
}
```

```json
// Response - 정상적으로 등록된 경우
{"status": 200, "data": "댓글이 정상적으로 등록 되었습니다."}

// Response - 문제가 발생한 경우
{"status": 400, "data": "에러가 발생하였습니다. (에러 메시지)"}
```