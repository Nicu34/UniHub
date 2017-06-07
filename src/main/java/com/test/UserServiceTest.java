package com.test;

import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class UserServiceTest {

    @Mock
    private UserDao invitedUserDao;

    @InjectMocks
    private UserService invitedUserService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        User invitedUser = new User();
        invitedUser.setId(10);
        when(invitedUserDao.findById(anyInt())).thenReturn(invitedUser);
        assertEquals(10, invitedUserService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        User schoolUser = new User();
        schoolUser.setId(10);
        when(invitedUserDao.findBySSO(anyString())).thenReturn(schoolUser);
        assertEquals(10, invitedUserService.findBySSO("hellothere").getId().intValue());
    }

    @Test
    public void findAll() {
        List<User> schoolUsers = Arrays.asList(new User(), new User(), new User());
        when(invitedUserDao.findAllUsers(anyObject())).thenReturn(schoolUsers);
        assertEquals(3, invitedUserService.findAllUsers(new University()).size());
    }
}