package com.imaginecup.entz.domain;

import lombok.*;

import javax.persistence.*;

@Data   // get, set 대신
@Builder // 빌더를 사용할 수 있게 함
@Table(name = "peer") //  테이블 명과 클래스 명이 같으면 생략 가능
@AllArgsConstructor // 모든 인자 들어간 생성자 대신
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 인자 없는 생성자
@Entity
public class Peer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 키 값을 자동생성
    private Long id;        // 사용자 id
    @Column
    private String type;
    @Column(nullable = false)
    private Long room_id;
    @Column
    private String sender;
    @Column
    private String offer;
    @Column
    private String answer;

}
