package org.sh.backend1.service;

import org.sh.backend1.domain.Comment;
import org.sh.backend1.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    Comment saveComment(Long boardId, CommentDTO commentDTO);
    List<Comment> getComments(Long boardId); //boardId로 댓글을 전부 갖고 올 수 있음
    public void deleteComment(Long id);
    Comment getComment(Long id);
    //댓글은 수정 잘 안하니까 안만듦.
}
