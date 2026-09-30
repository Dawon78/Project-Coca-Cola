package cocacola.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cocacola.mvc.domain.MemberDTO;
import cocacola.mvc.service.MemberService;

@Controller
@RequestMapping("/cocacola")
public class PasswordController {

    @Autowired
    private MemberService memberService;

    // 비밀번호 찾기 페이지 (GET)
    @GetMapping("/findPass")
    public String findPasswordPage() {
        return "cocacola/FindPass";  // JSP 파일 이름
    }

    // 비밀번호 찾기 처리 (POST)
    @PostMapping("/findPass")
    public String findPassword(MemberDTO dto, Model model) {

        MemberDTO member = memberService.findMemberByIdAndEmail(dto);

        if (member == null) {
            model.addAttribute("msg", "아이디 또는 이메일이 일치하지 않습니다.");
            model.addAttribute("success", false);
            return "cocacola/FindPass";
        }

        model.addAttribute("msg", "비밀번호는 " + member.getUserpw() + " 입니다.");
        model.addAttribute("success", true);

        return "cocacola/FindPass";
    }
}

