package org.sh.backend1.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sh.backend1.domain.Board;
import org.sh.backend1.domain.Comment;
import org.sh.backend1.dto.CommentDTO;
import org.sh.backend1.repository.BoardRepository;
import org.sh.backend1.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;


    @Override
    public Comment saveComment(Long boardId, CommentDTO commentDTO) { //댓글 저장 메서드
        Board board = boardRepository.findById(boardId).orElseThrow(); //boardId를 Board에서 갖고옴
        Comment comment = Comment.builder() //CommentDTO랑 Comment랑 둘이 다르기에, builder로 entity 객체 생성 해 줌.
                .board(board) //board는, board에서 갖고옴
                .content(commentDTO.getContent()) //content는, commentDTO에서 @Data로 getContent함.
                .writer(commentDTO.getWriter())
                .build();
        if(commentDTO.getParentId() != null) { //첫 번째 댓글인지 체크
            Comment parent = commentRepository.findById(commentDTO.getParentId()).orElseThrow(); //대댓글인 경우, 부모댓글Id를 가져옴
            comment.setParent(parent); //parentId 저장
        }
        //board.setCommentCount(board.getCommentCount() + 1); //board에 getCount+1로 setCommentCount함
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getComments(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow();

        return commentRepository.findByBoardAndParentIsNull(board);
    }

    @Override
    public void deleteComment(Long Id) {
        commentRepository.deleteById(Id);

    }
    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id).orElseThrow();
    }
}

