package com.techevents.techevents.service;

import com.techevents.techevents.entity.Users;
import com.techevents.techevents.repository.UsersRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    private UsersServiceImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new UsersServiceImpl(usersRepository);

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
        Users users = new Users(
                "glaucia",
                "mesquita",
                "123456789",
                "glaucia@glaucia.com"
        );
        //when
        underTest.save(users);

        //then
        ArgumentCaptor<Users> usersArgumentCaptor =
                ArgumentCaptor.forClass(Users.class);

        verify(usersRepository).save(usersArgumentCaptor.capture());

        Users capturedUsers = (Users) usersArgumentCaptor.getAllValues();
        assertThat(capturedUsers).isEqualTo(users);
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void delete() {
    }

    @Test
    @Disabled
    void listEvents() {
    }

    @Test
    @Disabled
    void findByUsername() {
    }
}