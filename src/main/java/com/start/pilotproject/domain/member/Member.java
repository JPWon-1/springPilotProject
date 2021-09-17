package com.start.pilotproject.domain.member;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.start.pilotproject.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member extends BaseTimeEntity {

    @Id
    @SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq")
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    // @OneToMany
    // @JoinColumn(name = "member_id")
    // private List<Comment> comments = new ArrayList<>();
    // 내 생각에 부모에 아이디가 아직 없어서 
    // 무결성 에러가 발생하는 듯? 나중에 조금 더 알아봐야 할듯.

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;
    private String providerId;

    private Boolean locked;
    private Boolean enabled;

    @Builder
    public Member(String firstName, 
                  String lastName, 
                  String email, 
                  String password, 
                  Role role,
                  String provider,
                  String providerId
                  ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    public void bcryptionPasswordAndGiveRole(String password){
        this.password = password;
        this.role = Role.USER;
    }
}
