// package com.start.pilotproject.domain.comment;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.SequenceGenerator;

// import lombok.Getter;
// import lombok.NoArgsConstructor;

// @Getter
// @NoArgsConstructor
// @Entity
// public class Comment {
//     @Id
//     @SequenceGenerator(name="comment_seq", sequenceName="comment_seq", allocationSize=1)
// 	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="comment_seq")
//     private Long id;

//     @Column(name="history_id")
//     private Long historyId;

//     @Column(name="member_id")
//     private Long memberId;

//     @Column
//     private String subject;

//     @Column
//     private String content;
// }
