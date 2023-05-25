package tacos.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tacos.dto.PostDTO;

@Controller
@RequestMapping("/post")
public class PostController {
    @GetMapping("/new")
    public String editPostPage(Model model, Authentication authentication) {
        model.addAttribute("author", authentication.getName());
        return "edit-post";
    }

    @PostMapping("/new")
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDto, HttpSession httpSession) {
        // 요청으로부터 전달받은 데이터를 사용하여 게시글 생성 로직을 수행합니다.
        // 여기서는 단순히 모델에 값을 저장하는 예시로 작성했습니다.
        httpSession.setAttribute("1", postDto);
        httpSession.setAttribute("author", httpSession.getAttribute("userName"));
        // 생성된 게시글의 ID를 반환하거나, 생성 결과 메시지를 반환할 수 있습니다.
        // 여기서는 단순히 성공 메시지를 반환하도록 작성했습니다.
        return ResponseEntity.ok("게시글이 성공적으로 작성되었습니다.");
    }

    @GetMapping("/{postId}")
    public String postPage(@PathVariable("postId")String postId,HttpSession httpSession, Model model) {
        PostDTO postDto = (PostDTO)httpSession.getAttribute(postId);
        if ( postDto==null )
            return "error404";
        model.addAttribute("author", httpSession.getAttribute("author"));
        model.addAttribute("title", postDto.getTitle());
        model.addAttribute("content", postDto.getContent());
        return "post";
    }

    @GetMapping("/{postId}/update")
    public String postUpdatePage(@PathVariable("postId")String postId, HttpSession httpSession, Model model) {
        PostDTO postDto = (PostDTO) httpSession.getAttribute(postId);
        if ( postDto==null )
            return "error404";
        model.addAttribute("title", postDto.getTitle());
        model.addAttribute("content", postDto.getContent());
        return "mod-post";
    }
}
