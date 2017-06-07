package com.test;

/**
 * Created by nicu on 6/7/2017.
 */

import com.websystique.springmvc.dao.CourseDao;
import com.websystique.springmvc.model.Course;
import com.websystique.springmvc.service.CourseService;
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
public class CourseServiceTest {

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseService courseService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        Course course = new Course();
        course.setId(10);
        when(courseDao.findById(anyInt())).thenReturn(course);
        assertEquals(10, courseService.findById(10).getId().intValue());
    }
}