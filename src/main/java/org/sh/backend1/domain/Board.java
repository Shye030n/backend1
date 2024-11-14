package org.sh.backend1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity //JPA 엔티티 클래스
@Data //Getter Setter toString 자동 생성
@NoArgsConstructor //default 생성자 자동 생성
@AllArgsConstructor // 모든 필드를 초기화하는 생성자, 매개변수 있는 생성자 자동 생성
@Builder // build().builder(); 사용 => 체이닝 방식으로 객체 생성
public class Board {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AI
    private Long id;
    private String title;
    private String content;
    private String writer;
    private Integer commentCount; //댓글 개수

    @CreationTimestamp // 현재 시각으로 DB에 자동 업로드 설정
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regdate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @PrePersist //entity가 DB에 저장되기 전에 먼저 실행되는 메서드에 붙임, 주로 초기값 설정 or 데이터 무결성 보장을 위해 사용.
    public void prePersist() { //댓글 개수 메서드
        this.commentCount = commentCount == null ? 0 : commentCount; // commentCount가 null일때 true->0, false->commentCount
        //this.commentCount field(=인스턴스 변수)에꺼 사용
        //commentCount 생성자 parameter(생성자 매개변수)에꺼 사용
    }

}
