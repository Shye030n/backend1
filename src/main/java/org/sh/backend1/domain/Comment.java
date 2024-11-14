package org.sh.backend1.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private String writer;

    @CreationTimestamp //현재 시각으로 자동 업로드 설정
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd") //날짜 표기법 설정 초기화
    private LocalDateTime regdate;

    @ManyToOne(fetch = FetchType.LAZY) //다대일관계 지연로딩(LazeLoding) 설정 - 데이터 갖고오는 방식
    @JoinColumn(name = "board_id", nullable = false) //board_id와 조인
    @JsonIgnore //순환참조를 막아줌(꼭 넣어야 함)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") //parent_id와 조인
    @JsonIgnore //한 댓글에 부모를 불러오면 자식과의 순환참조를 방지
    private Comment parent; // 부모댓글 영역(대댓글 달 때 기존 댓글)
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL) //한 댓글에 여러개의 대댓글이 달림.
    private List<Comment> childeren = new ArrayList<>(); //리스트는 객체로 불러와야 함.


}
