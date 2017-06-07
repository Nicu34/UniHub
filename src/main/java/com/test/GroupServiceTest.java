package com.test;

import com.websystique.springmvc.dao.GroupDao;
import com.websystique.springmvc.model.SchoolGroup;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.service.GroupService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class GroupServiceTest {

    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private GroupService groupService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        SchoolGroup group = new SchoolGroup();
        group.setId(10);
        when(groupDao.findById(anyInt())).thenReturn(group);
        assertEquals(10, groupService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        SchoolGroup schoolGroup = new SchoolGroup();
        schoolGroup.setId(10);
        when(groupDao.findByGroupNumber(anyLong())).thenReturn(schoolGroup);
        assertEquals(10, groupService.findByGroupNumber(123L).getId().intValue());
    }

    @Test
    public void findAll() {
        List<SchoolGroup> schoolGroups = Arrays.asList(new SchoolGroup(), new SchoolGroup(), new SchoolGroup());
        when(groupDao.findAllGroups(any(University.class))).thenReturn(schoolGroups);
        assertEquals(3, groupService.findAll(new University()).size());
    }
}