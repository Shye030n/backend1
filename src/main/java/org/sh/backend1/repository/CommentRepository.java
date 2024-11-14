package org.sh.backend1.repository;

import org.sh.backend1.domain.Board;
import org.sh.backend1.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //레파짓토리로 사용할 엔티티가 Comment
    //레파짓토리로 사용할 id 필드의 데이터 타입이 Long

    List<Comment> findByBoardAndParentIsNull(Board board); //CommentEntity 안에 board와 parent(null)인 field
        // => 댓글 중에 최상위 댓글만 찾는 method(이 안에 children 있음)
}
