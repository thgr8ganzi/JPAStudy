package com.example.study.repository;

import com.example.study.dto.MemberDto;
import com.example.study.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void testMember(){
        Member member = new Member("memberA");
        Member saveMember = memberRepository.save(member);

        Member members = memberRepository.findById(saveMember.getId()).get();
    }

    @Test
    public void returnTest(){
        Optional<Member> member = memberRepository.findById(26L);
        
        Optional<Member> member1 = memberRepository.findOptionByUsername(member.get().getUsername());
        System.out.println("member1 = " + member1);

    }

    @Test
    public void paging(){
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));

        Page<Member> page = memberRepository.findByAge(age, pageRequest);

        List<Member> content = page.getContent();
        Long totalElements = page.getTotalElements();

        Page<MemberDto> toMap = page.map(member -> new MemberDto(member.getId(), member.getUsername(), null));

        for (Member member : content) {
            System.out.println("member = " + member);
        }
        System.out.println("totalElements = " + totalElements);

        int size = content.size();
        System.out.println("size = " + size);
        int totalPages = page.getTotalPages();
        System.out.println("totalPages = " + totalPages);
        int number = page.getNumber();
        System.out.println("number = " + number);
        boolean first = page.isFirst();
        System.out.println("first = " + first);
        boolean b = page.hasNext();
        System.out.println("b = " + b);

    }

    @Test
    public void bulkUpdate(){
        int i = memberRepository.bulkAgePlus(10);
        System.out.println("i = " + i);
    }


}













