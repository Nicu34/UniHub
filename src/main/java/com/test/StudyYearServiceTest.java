package com.test;

import com.websystique.springmvc.dao.StudyYearDao;
import com.websystique.springmvc.model.StudyYear;
import com.websystique.springmvc.model.University;
import com.websystique.springmvc.service.StudyYearService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class StudyYearServiceTest {

    @Mock
    private StudyYearDao studyYearDao;

    @InjectMocks
    private StudyYearService studyYearService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        StudyYear studyYear = new StudyYear();
        studyYear.setId(10);
        when(studyYearDao.findById(anyInt())).thenReturn(studyYear);
        assertEquals(10, studyYearService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        StudyYear schoolStudyYear = new StudyYear();
        schoolStudyYear.setId(10);
        when(studyYearDao.findByYear(anyInt(), anyObject())).thenReturn(schoolStudyYear);
        assertEquals(10, studyYearService.findByYearAndUniversity(134, new University()).getId().intValue());
    }

    @Test
    public void findAll() {
        List<StudyYear> schoolStudyYears = Arrays.asList(new StudyYear(), new StudyYear(), new StudyYear());
        when(studyYearDao.findAllStudyYears(anyObject())).thenReturn(schoolStudyYears);
        assertEquals(3, studyYearService.findAll(new University()).size());
    }
}