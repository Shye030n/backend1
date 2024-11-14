package org.sh.backend1.service;

import lombok.RequiredArgsConstructor;
import org.sh.backend1.domain.Board;
import org.sh.backend1.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional //Service에서 Board라는 테이블 하나에만 접근할 때는 필요 없지만, 두 개 이상의 테이블 접근할 때 동시 접근, 동시 비접근을 실행해줌.
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository; //final 한 번 결정되면 안바뀜.
    //@Override 이거랑, @RequiredArgsConstructor써서 위에꺼 쓰는 방법 중 선택.
    //private BoardRepository boardRepository

    @Override
    public void insert(Board board) {
        boardRepository.save(board);
    }

    @Override
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @Override
    public Board findById(Long id) {
        //Optional<Board> board = boardRepository.findById(id);
        return boardRepository.findById(id). orElseThrow(null);
    }

    @Override
    public void update(Board board) {
        Board oldBoard = boardRepository.findById(board.getId()).orElseThrow(null);
        oldBoard.setTitle(board.getTitle());
        oldBoard.setContent(board.getContent());
        boardRepository.save(oldBoard);
    }

    @Override
    public void delete(Long num) {
        boardRepository.deleteById(num);
    }
}
