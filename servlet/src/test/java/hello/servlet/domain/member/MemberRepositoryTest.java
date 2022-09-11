package hello.servlet.domain.member;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    //테스트가 끝날떄마다 초기화 테스트의 순서가 보장되지 않기 때문에 각 테스트를 개별적으로 테스트하기 위해서 필요
    @AfterEach
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given 주어졌을떄
        Member member = new Member("hello",20);
        //when 실행했을떄
        Member savedMember = memberRepository.save(member);
        //then 결과가 이래야해
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll(){
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",20);

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> result = memberRepository.findAll();

        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1,member2);
    }
}
