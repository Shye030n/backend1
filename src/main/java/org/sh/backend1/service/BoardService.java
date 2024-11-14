package org.sh.backend1.service;

import org.sh.backend1.domain.Board;

import java.util.List;

public interface BoardService {
    // (지금처럼) 간단하면 인터페이스를 만들필요 없으나, 실전에서는 대부분 인터페이스 사용.

    void insert(Board board); // 외부에서 쓰려면 public을 써야하지만, 앞에 생략되어 있음
    List<Board> list(); // 외부에서 쓰려면 public을 써야하지만, 앞에 생략되어 있음
    Board findById(Long num);
    void update(Board board);
    void delete(Long num);
}
