package org.sh.backend1.dto;

import lombok.Data;

@Data
public class CommentDTO {
    //CommentDTO는 데이터를 받을 때 사용할 거라서 필드를 세 가지만 씀. 출력 부분은 쓰지 않음.
    //entity로만 데이터 입력 및 return이 충분하다면 DTO를 만들지 않아도 되지만, Comment entity가 parentId를 받지 못하기 때문에 DTO 만듦
    private String content;
    private String writer;
    private Long parentId;
}
