![22](https://github.com/Torres-09/hellolaw/assets/76430979/9aceb087-e23a-4fdd-bd14-8abc58c5313a)

## 목차
0. [구현 사항](#0-구현-사항)
1. [서비스 개요](#1-서비스-개요)
2. [기술 스택](#2-기술-스택)
3. [시스템 아키텍처](#3-시스템-아키텍처)
4. [파일 구조](#4-파일-구조)
5. [ERD](#5-erd)

## 0. 구현 사항
### [MVC 공통 로직](back-auth)  
### [WebFlux 공통 로직](back-auth-v2)  
### [Core API](hellolaw)  
### [AI 추론](hellolaw-ai)

## 1. 서비스 개요

AI 기반의 법률 조언 서비스 "헬로"는 기존 법률 서비스의 상담 비용과 절차, 접근 난이도를 줄이고자 기획되었습니다. 

AI를 통해 사용자의 상황에 맞는 과거 사례와 판례, 법안들을 제공하고, 언제 어디서나 쉽고 간편하게 접근이 가능한 법률 조언 서비스 "헬로"입니다.

## 2. 기술 스택

### IDE
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellij idea&logoColor=white"> <img src="https://img.shields.io/badge/visual studio code-007ACC?style=for-the-badge&logo=visualstudiocode&logoColor=white"> <img src="https://img.shields.io/badge/pycharm-143?style=for-the-badge&logo=pycharm&logoColor=black&color=black&labelColor=green">

### Database
<img src="https://img.shields.io/badge/mysql 5.6-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/redis 7.2.4-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white">

### AI Server
<img src="https://img.shields.io/badge/python 3.12.0-3670A0?style=for-the-badge&logo=python&logoColor=ffdd54"> <img src="https://img.shields.io/badge/FastAPI-005571?style=for-the-badge&logo=fastapi">

### Backend - API Server
![Java](https://img.shields.io/badge/java17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
<img src="https://img.shields.io/badge/spring boot 3.2.5-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=hibernate&logoColor=white">

### Backend - Auth Server
![Java](https://img.shields.io/badge/java17-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
<img src="https://img.shields.io/badge/spring boot 3.2.5-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
<img src="https://img.shields.io/badge/spring webflux-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white">
<img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white">
<img src="https://img.shields.io/badge/Spring Data JPA-6DB33F?style=for-the-badge&logo=hibernate&logoColor=white">
<img src="https://img.shields.io/badge/Spring Cloud Gateway-6DB33F?style=for-the-badge&logoColor=white">
<img src="https://img.shields.io/badge/JWT 0.8.0-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=FFFFFF">

### Infrastructure
<img src="https://img.shields.io/badge/jenkins-D24939?style=for-the-badge&logo=jenkins&logoColor=white"> <img src="https://img.shields.io/badge/docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/aws ec2-FF9900?style=for-the-badge&logo=amazon ec2&logoColor=white"> <img src="https://img.shields.io/badge/eks-58ACFA?style=for-the-badge&logo=Amazon%20EKS&logoColor=white"> <img src="https://img.shields.io/badge/k8s-326CE5?style=for-the-badge&logo=Kubernetes&logoColor=white"> <img src="https://img.shields.io/badge/fluentd-0E83C8?style=for-the-badge&logo=Fluentd&logoColor=white">

### Version control
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/gitlab-FC6D26?style=for-the-badge&logo=gitlab&logoColor=white">


### Management Tools
<img src="https://img.shields.io/badge/jira software-0052CC?style=for-the-badge&logo=jira software&logoColor=white"> <img src="https://img.shields.io/badge/mattermost-0058CC?style=for-the-badge&logo=mattermost&logoColor=white"> <img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white"> <img src="https://img.shields.io/badge/figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">


## 3. 시스템 아키텍처
![architecture](https://github.com/Torres-09/hellolaw/assets/76430979/494c58fc-8b0b-40ad-ab8d-ff779e824887)

## 4. 파일 구조

### API Server
```
📁
├─main
│  ├─java
│  │  └─com
│  │      └─hellolaw
│  │          └─hellolaw
│  │              ├─annotation
│  │              ├─common
│  │              ├─config
│  │              ├─controller
│  │              ├─dto
│  │              ├─entity
│  │              │  └─common
│  │              ├─exception
│  │              ├─filter
│  │              ├─internal
│  │              │  ├─config
│  │              │  ├─dto
│  │              │  └─service
│  │              ├─mapper
│  │              ├─repository
│  │              ├─resolver
│  │              ├─service
│  │              └─util
│  └─resources
└─test
    └─java
        └─com
            └─hellolaw
                └─hellolaw
```

### Auth Server
```
📁
├─main
│  └─java
│      └─com
│          └─hellolaw
│              └─auth
│                  ├─client
│                  │  └─kakao
│                  ├─config
│                  ├─controller
│                  ├─dto
│                  ├─entity
│                  ├─exception
│                  ├─filter
│                  ├─provider
│                  ├─redis
│                  ├─repository
│                  ├─service
│                  └─util
└─test
    └─java
        └─com
            └─hellolaw
                └─auth
```

### AI Server
```
📁
└─AI
    ├─BERT
    │  ├─inference
    │  ├─make_embedding
    │  └─search
    ├─GPTAssistant
    ├─LLM
    └─utils
```

## 5. ERD
![image](https://github.com/Torres-09/hellolaw/assets/76430979/686bf430-0a42-4324-ae4d-97b5e558eb3c)
