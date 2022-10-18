package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save: member={}",member);
        store.put(member.getId(),member);
        return member;
    }

    public Member findId(Long id){
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){

//        List<Member> memberList = findAll();
//        for (Member member : memberList) {
//            if(member.getLoginId().equals(loginId)){
//                return Optional.of(member);
//            }
//        }
//        return Optional.empty();

        return findAll().stream().
                filter(m -> m.getLoginId().equals(loginId)).findFirst();
    }
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
