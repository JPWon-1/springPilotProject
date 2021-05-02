package com.start.pilotproject.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.start.pilotproject.util.BaseTimeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {
    @Id
    @SequenceGenerator(name="user_seq", sequenceName="user_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="user_seq")
    private Long seq;

    @Column
    private Email email;
    
    @Column
    private String name;

    @Enumerated
    @Column(nullable=false)
    private Role role;

    public User(Email email, String name, Role role) {
        this.email = email;
        this.name = name;
        this.role = role;
    }
    public static class Builder{
        private Email email;
        private String name;
        private Role role;

        public Builder(){
        }

        public Builder(User user){
            this.email = user.email;
            this.name = user.name;
            this.role = user.role;
        }

        public Builder email(Email email){
            this.email = email;
            return this;
        }
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder role(Role role){
            this.role = role;
            return this;
        }
        public User build(){
            return new User(email, name , role);
        }

    }
}
