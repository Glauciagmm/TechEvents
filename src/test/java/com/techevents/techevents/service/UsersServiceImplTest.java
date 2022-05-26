package com.techevents.techevents.service;

import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    private UsersServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UsersServiceImpl(usersRepository, passwordEncoder);

    }

    @Test
    void canGetAllUsers() {
        //when
        underTest.findAll();
        //then
        verify(usersRepository).findAll();
    }

    @Test
    void canSaveUser() {
        //given
        Users user = new Users(
                "glaucia",
                "mesquita",
                "123456789",
                "glaucia@glaucia.com"
        );

        //when
        underTest.save(user);

        //then
        ArgumentCaptor<Users> usersArgumentCaptor =
                ArgumentCaptor.forClass(Users.class);

        verify(usersRepository).save(usersArgumentCaptor.capture());

        Users capturedUsers = usersArgumentCaptor.getValue();
        assertThat(capturedUsers).isEqualTo(user);
    }

    @Test
    void canFindUserById() {
        Users userId = new Users(
                2L,
                "glaucia",
                "mesquita"
                );

        //when
        underTest.findById(userId.getId());

        //then
        verify(usersRepository).findById(2L);

    }

    @Test
    void canDeleteUser() {
        Users userDelete  = new Users(
                1L,
                "glaucia",
                "mesquita"
        );
        underTest.delete(userDelete.getId());
        verify(usersRepository).deleteById(1L);
    }
}