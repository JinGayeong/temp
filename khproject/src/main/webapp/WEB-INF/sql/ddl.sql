CREATE USER infuser
identified by infpw;

--권한주기
grant connect to infuser;
grant resource to infuser;
grant dba to infuser;

-- 멤버 테이블 생성
create table member (
memno number,
nickname varchar(20),
id varchar(20) not null,
phonenum int not null,
email varchar(100) not null,
pw varchar(20),
name varchar(10),
address varchar(100),
companyno varchar(50) not null,
birth int,
memimg varchar(500),
rank int,

primary key (memno, nickname)
);


-- 게시판 테이블 생성
create table board (
boardNo NUMBER,
id VARCHAR2(100),
nickname VARCHAR2(100),
memNo NUMBER,
title VARCHAR2(100),
regDate DATE,
modifiedDate DATE,
category VARCHAR2(100),
address VARCHAR2(100),
area NUMBER,
startDate DATE,
endDate DATE,
budget VARCHAR2(100),
part VARCHAR2(100),
require VARCHAR2(1000),
image VARCHAR2(100),
view_count NUMBER
);


CREATE SEQUENCE boardNo_seq START WITH 1 INCREMENT BY 1;



--QNA 문의 테이블 생성
create table QNA (
    qnaNo Number,
    memNo Number,
    nickname varchar2(20),
    qnaTitle VARCHAR2(255),
    qnaDate date,
    answerDate date,
    status VARCHAR2(10),

    CONSTRAINT QNA_pk PRIMARY KEY(qnaNo),
    constraint status_ck check (status = 1 or status = 0)
);

CREATE SEQUENCE qna_ID INCREMENT BY 1;

create table QNAContent(
    qnaNo Number,
    qnaContent VARCHAR2(300),

    constraint QNA_fk foreign key(qnaNo)
    references QNA(qnaNo)
    on delete cascade
);
