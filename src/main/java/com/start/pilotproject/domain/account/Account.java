package com.start.pilotproject.domain.account;

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
public class Account extends BaseTimeEntity {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long seq;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Enumerated
    // @Column(nullable = false)
    private Role role;
    public static class Builder {
        private String email;
        private String name;
        private String password;
        private Role role;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

		public Account build() {
			return new Account(this);
		}
    }
    private Account(Builder builder){
        email = builder.email;
        name = builder.name;
        password = builder.password;
        role = builder.role;
    }
}
