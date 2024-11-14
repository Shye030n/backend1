package org.sh.backend1.controller;
import org.sh.backend1.domain.Board;
import org.sh.backend1.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/register")
    public void register() {

    }
    @PostMapping("/register")
    public String registerPost(Board board, Model model) {
        boardService.insert(board);
        return "redirect:/board/list"; //redirect는 Get이기에 새 요청(BoardController의 list).
    }
    @GetMapping("/list")
    public void list(Model model) {
        model.addAttribute("boards", boardService.list()); //boards라는 이름으로 모든 데이터를 가져와서 저장
    }
    @GetMapping("/read/{id}") //경로에 자원{id}이 들어감.
    public String read(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.findById(id));
        return "/board/read";
    }
    @GetMapping("/modify/{id}")
    public String modify(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.findById(id));
        return "/board/modify";
    }
    @PostMapping("/modify/{id}")
    public String modifyPost(Board board, Model model) {
        boardService.update(board);
        return "redirect:/board/read/"+board.getId();
    }
    @GetMapping("/remove")
    public String remove(Long id){
        boardService.delete(id);
        return "redirect:/board/list";
    }
}
