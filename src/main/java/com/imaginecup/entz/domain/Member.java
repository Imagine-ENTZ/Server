package com.imaginecup.entz.domain;

import lombok.*;

import javax.persistence.*;


@Data   // get, set 대신
@Builder // 빌더를 사용할 수 있게 함
@Table(name = "Member") //  테이블 명과 클래스 명이 같으면 생략 가능
@AllArgsConstructor // 모든 인자 들어간 생성자 대신
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 생성자
@Entity
public class Member {


    @Column(name="id")
    @Id
    private String user;  // 사용자 아이디
    @Column(nullable = false)
    private String name;    // 사용자 이름
    @Column
    private String password;      // 사용자 패스워드

}
