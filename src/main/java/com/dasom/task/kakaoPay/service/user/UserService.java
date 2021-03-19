package com.dasom.task.kakaoPay.service.user;

import com.dasom.task.kakaoPay.model.member.Member;
import com.dasom.task.kakaoPay.model.user.User;
import com.dasom.task.kakaoPay.repository.user.UserRepository;
import com.dasom.task.kakaoPay.service.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MemberService memberService;


    public void joinUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));

        Integer userId = userRepository.joinUser(user);
        addMember(userId);

    }

    private void addMember(int userId) {
        Member member = memberService.getMemberInfo(userId);
        member.setName(member.getName());
        member.setGradeId(4);
        memberService.addMember(member);
    }

    /**
     * Spring Security 필수 메소드 구현
     *
     * @param username 아이디
     * @return UserDetails
     * @throws UsernameNotFoundException 유저가 없을 때 예외 발생
     */
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("##### login #####");
        User user = userRepository.findById(username);
        if(user == null){
            log.debug("계정정보없음");
            throw new UsernameNotFoundException(username);
        }

//        return User.builder()
//                .userNo(user.getUserNo())
//                .userName(user.getUsername())
//                .memberId(user.getMemberId())
//                .build();
        return user;
    }
}
