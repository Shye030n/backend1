package org.sh.backend1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.sh.backend1.domain.Comment;
import org.sh.backend1.dto.CommentDTO;
import org.sh.backend1.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}") //댓글 전체 가져올 때
    public ResponseEntity<Long> addComment(@PathVariable("boardId") Long boardId,
       //ResponseEntity를 사용하는 이유는 상태 정보랑 같이 보내려고 <ㅡ> Comment(입력받은 데이터 전달 시)
                                        @RequestBody CommentDTO commentDTO){  //RequestBody는 외부 데이터를 CommentDTO 객체가 받게 함.

        Comment comment = commentService.saveComment(boardId,commentDTO);
        return ResponseEntity.ok(comment.getId()); // 댓글의 아이디를 보냄.
    }
    @GetMapping("/{boardId}") //댓글 전체 가져올 때
    public ResponseEntity<List<Comment>> getComments(@PathVariable("boardId") Long boardId){
        return ResponseEntity.ok(commentService.getComments(boardId));
    }
    @GetMapping("/one/{id}") //댓글 하나만 가져올 때
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id){
        return ResponseEntity.ok(commentService.getComment(id));
    }

    @DeleteMapping("/{id}") //댓글 삭제할 때
    public ResponseEntity<Long> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity.ok(id);
    }
}
