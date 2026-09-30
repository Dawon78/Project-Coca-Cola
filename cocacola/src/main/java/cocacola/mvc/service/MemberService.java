package cocacola.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cocacola.mvc.domain.MemberDTO;
import cocacola.mvc.mapper.MemberMapper;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    // 아이디 찾기: 입력 전화번호의 하이픈을 제거한 뒤 회원 조회
    // 없는 회원이면 null 반환
    public MemberDTO findMemberByPhone(MemberDTO dto) {
        if (dto.getPhone() != null) {
            dto.setPhone(dto.getPhone().replace("-", ""));
        }
        return memberMapper.findByPhone(dto);
    }

    // 비밀번호 찾기: 아이디 + 이메일로 회원 조회
    // 없는 회원이면 null 반환
    public MemberDTO findMemberByIdAndEmail(MemberDTO dto) {
        return memberMapper.findByIdAndEmail(dto);
    }
}
