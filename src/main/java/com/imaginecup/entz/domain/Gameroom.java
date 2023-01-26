package com.imaginecup.entz.domain;

import lombok.*;

import javax.persistence.*;

@Data   // get, set 대신
@Builder // 빌더를 사용할 수 있게 함
@Table(name = "gameroom") //  테이블 명과 클래스 명이 같으면 생략 가능
@AllArgsConstructor // 모든 인자 들어간 생성자 대신
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 생성자
@Entity
public class Gameroom {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;  // 방코드
    @Column
    private String name;    // 방이름
    @Column
    private String type;  // 게임종류
    @Column
    private Long full; //입장여부
}
