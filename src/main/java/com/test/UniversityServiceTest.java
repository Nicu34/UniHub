package com.test;

import com.websystique.springmvc.dao.UniversityDao;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.service.UniversityService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class UniversityServiceTest {

    @Mock
    private UniversityDao universityDao;

    @InjectMocks
    private UniversityService universityService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        University university = new University();
        university.setId(10);
        when(universityDao.findById(anyInt())).thenReturn(university);
        assertEquals(10, universityService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        University schoolUniversity = new University();
        schoolUniversity.setId(10);
        when(universityDao.findByShortName(anyString())).thenReturn(schoolUniversity);
        assertEquals(10, universityService.findByShortName("hellothere").getId().intValue());
    }
}