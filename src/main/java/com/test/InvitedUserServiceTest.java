package com.test;

import com.websystique.springmvc.dao.InvitedUserDao;
import com.websystique.springmvc.model.InvitedUser;
import com.websystique.springmvc.service.InvitedUserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class InvitedUserServiceTest {

    @Mock
    private InvitedUserDao invitedUserDao;

    @InjectMocks
    private InvitedUserService invitedUserService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        InvitedUser invitedUser = new InvitedUser();
        invitedUser.setId(10);
        when(invitedUserDao.findById(anyInt())).thenReturn(invitedUser);
        assertEquals(10, invitedUserService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        InvitedUser schoolInvitedUser = new InvitedUser();
        schoolInvitedUser.setId(10);
        when(invitedUserDao.findBySsoId(anyString())).thenReturn(schoolInvitedUser);
        assertEquals(10, invitedUserService.findBySsoId("hellothere").getId().intValue());
    }

    @Test
    public void findAll() {
        List<InvitedUser> schoolInvitedUsers = Arrays.asList(new InvitedUser(), new InvitedUser(), new InvitedUser());
        when(invitedUserDao.findAllInvitedUsers()).thenReturn(schoolInvitedUsers);
        assertEquals(3, invitedUserService.findAll().size());
    }
}