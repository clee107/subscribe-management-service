# API Specification

## 1. 채널 구독 관련

### 1.1. 구독하기

```http
POST /v1/channel/subscription/join
```

#### 설명

휴대폰 번호, 채널 ID, 구독 요청 타입을 입력 받아 해당 채널에 회원을 구독 등록합니다.

#### Request Body

| Name             | Type        | Required | Description                              |
|------------------|-------------|----------|------------------------------------------|
| channelId        | Integer     | Yes      | 채널 순번                                    |
| phone            | String      | Yes      | 회원 연락처 (e.g., 0100000000)                |
| subscriptionType | Enum String | Yes      | 구독 타입 (GENERAL: 일반 구독, PREMIUM: 프리미엄 구독) |

#### Response

성공적으로 구독 완료 시 200 OK 반환.

실패 시, 200 외의 response code 와 함께 message 반환. 예를 들어, 휴대폰 번호 포맷이 잘못 되었을 경우, HTTP 400 과 함께 아래 Body 반환.

```json
{
  "message": "Bad request"
}
```

### 1.2. 구독 해지하기

```http
POST /v1/channel/subscription/withdraw
```

#### 설명

휴대폰 번호, 채널 ID, 구독 해지 요청 타입을 입력 받아, 회원의 해당 채널 구독을 해지합니다.

#### Request Body

| Name             | Type        | Required | Description                               |
|------------------|-------------|----------|-------------------------------------------|
| channelId        | Integer     | Yes      | 채널 순번                                     |
| phone            | String      | Yes      | 회원 연락처 (e.g., 0100000000)                 |
| subscriptionType | Enum String | Yes      | 변경할 구독 타입 (CANCEL: 구독 취소, GENERAL: 일반 구독) |

#### Response

성공적으로 구독 완료 시 200 OK 반환. 실패 시, 200 외의 response code 와 함께 message 반환.

## 2. 구독 및 채널 이력 조회 관련

### 2.1. 구독 이력 조회

```http
GET /v1/channel/subscription/history/{phone}
```

#### 설명

휴대폰 번호를 입력 받아, 해당 회원의 일자별, 채널 별 구독 상태 이력을 조회합니다.

#### Path Parameters

| Name  | Type   | Required | Description               |
|-------|--------|----------|---------------------------|
| phone | String | Yes      | 회원 연락처 (e.g., 0100000000) |

#### Response

조회 성공 시 200 OK 및 구독 상태 이력 반환

##### Response Body

| Name     | Type                     | Description      |
|----------|--------------------------|------------------|
| memberId | Integer                  | 회원 순번            |
| phone    | String                   | 회원 연락처           |
| history  | SubscriptionHistory list | 날짜별 구독 이력 정보 리스트 |

##### SubscriptionHistory object

| Name                           | Type        | Description                                                |
|--------------------------------|-------------|------------------------------------------------------------|
| date                           | String      | 날짜 (yyyy-MM-dd)                                            |
| channels                       | object list | 각 채널별 이력 정보                                                |
| channels\[i\].channelId        | Integer     | 채널 순번                                                      |
| channels\[i\].channelName      | String      | 채널명                                                        |
| channels\[i\].subscriptionType | Enum String | 구독 요청 타입 (CANCEL: 구독 취소, GENERAL: 일반 구독, PREMIUM: 프리미엄 구독) |
| channels\[i\].createdAt        | String      | 등록 일시 (yyyy-MM-dd HH:mm:ss)                                |

##### Response Body 예시

```json
{
  "memberId": 1,
  "phone": "0100000000",
  "history": [
    {
      "date": "2024-09-28",
      "channels": [
        {
          "channelId": 1,
          "channelName": "기본 테스트 채널",
          "subscriptionType": "PREMIUM",
          "createdAt": "2024-09-28 20:29:23"
        }
      ]
    },
    {
      "date": "2024-09-29",
      "channels": [
        {
          "channelId": 1,
          "channelName": "기본 테스트 채널",
          "subscriptionType": "GENERAL",
          "createdAt": "2024-09-29 20:28:08"
        },
        {
          "channelId": 1,
          "channelName": "기본 테스트 채널",
          "subscriptionType": "PREMIUM",
          "createdAt": "2024-09-29 20:28:12"
        },
        {
          "channelId": 1,
          "channelName": "기본 테스트 채널",
          "subscriptionType": "CANCEL",
          "createdAt": "2024-09-29 20:28:16"
        },
        {
          "channelId": 2,
          "channelName": "구독만 가능한 채널",
          "subscriptionType": "GENERAL",
          "createdAt": "2024-09-29 20:28:23"
        },
        {
          "channelId": 2,
          "channelName": "구독만 가능한 채널",
          "subscriptionType": "PREMIUM",
          "createdAt": "2024-09-29 20:28:33"
        }
      ]
    }
  ]
}
```

### 2.2. 채널 이력 조회

```http
GET /v1/channel/history?channelId={channelId}&date={date}
```

#### 설명

채널 순번과 일자를 입력 받아, 채널에서 해당 날짜에 발생한 모든 회원의 구독 이력을 조회합니다.

#### Request Parameters

| Name      | Type    | Required | Description       |
|-----------|---------|----------|-------------------|
| channelId | Integer | Yes      | 채널 순번             |
| date      | String  | Yes      | 조회할 날짜 (yyyyMMdd) |

#### Response

조회 성공 시 200 OK 및 채널 구독 이력 반환

##### Response Body

| Name        | Type                            | Description         |
|-------------|---------------------------------|---------------------|
| memberId    | Integer                         | 회원 순번               |
| channelId   | Integer                         | 채널 순번               |
| channelName | String                          | 채널명                 |
| date        | String                          | 조회한 날짜 (yyyy-MM-dd) | 
| history     | ChannelSubscriptionHistory list | 채널 구독 이력 정보 리스트     |

##### ChannelSubscriptionHistory object

| Name        | Type        | Description                                                |
|-------------|-------------|------------------------------------------------------------|
| memberId    | Integer     | 회원 순번                                                      |
| phone       | String      | 회원 연락처                                                     |
| requestType | Enum String | 구독 요청 타입 (CANCEL: 구독 취소, GENERAL: 일반 구독, PREMIUM: 프리미엄 구독) |
| createdAt   | String      | 등록 일시 (yyyy-MM-dd HH:mm:ss)                                |

##### Response Body 예시

```json
{
  "channelId": 1,
  "channelName": "기본 테스트 채널",
  "date": "2024-09-29",
  "history": [
    {
      "memberId": 1,
      "phone": "0100000000",
      "requestType": "GENERAL",
      "createdAt": "2024-09-29 20:28:08"
    },
    {
      "memberId": 1,
      "phone": "0100000000",
      "requestType": "PREMIUM",
      "createdAt": "2024-09-29 20:28:12"
    },
    {
      "memberId": 1,
      "phone": "0100000000",
      "requestType": "CANCEL",
      "createdAt": "2024-09-29 20:28:16"
    }
  ]
}
```