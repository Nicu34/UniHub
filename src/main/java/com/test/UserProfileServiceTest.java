package com.test;

import com.websystique.springmvc.dao.ProfileDao;
import com.websystique.springmvc.model.Profile;
import com.websystique.springmvc.service.UserProfileService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class UserProfileServiceTest {

    @Mock
    private ProfileDao invitedUserDao;

    @InjectMocks
    private UserProfileService invitedUserService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        Profile invitedUser = new Profile();
        invitedUser.setId(10);
        when(invitedUserDao.findById(anyInt())).thenReturn(invitedUser);
        assertEquals(10, invitedUserService.findById(10).getId().intValue());
    }
}