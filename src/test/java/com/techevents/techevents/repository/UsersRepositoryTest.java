package com.techevents.techevents.repository;

import com.techevents.techevents.entity.Users;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();

    }

    @Test()
    void itShouldCheckIfaUserEmailExist (){
        //given
        String email = "glaucia@glaucia.com";
        Users users = new Users(
                "glaucia",
                "mesquita",
                123456789,
                email
        );
        underTest.save(users);
        //when

        Boolean expected = underTest.selectExistsEmail(email);
        //then

        assertThat(expected).isTrue();

    }
    @Test()
    void itShouldCheckIfaUserEmailDoesNotExist (){
        //given
        String email = "glaucia@glaucia.com";

        //when

        Boolean expected = underTest.selectExistsEmail(email);
        //then

        assertThat(expected).isFalse();

    }
    @Test
    void findByUsername() {
    }

    @Test
    void save() {
    }
}