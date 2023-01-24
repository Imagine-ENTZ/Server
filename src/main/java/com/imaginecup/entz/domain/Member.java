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


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 값을 자동생성
    private Long id;        // 사용자 id
    @Column
    private String name;    // 사용자 이름
    @Column
    private String gender;  // 사용자 성별
    @Column(nullable = false)
    private Long level;      // 사용자 레벨

}
