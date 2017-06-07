package com.test;

import com.websystique.springmvc.dao.TeacherDao;
import com.websystique.springmvc.model.Teacher;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.TeacherService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by nicu on 6/7/2017.
 */
public class TeacherServiceTest {

    @Mock
    private TeacherDao teacherDao;

    @InjectMocks
    private TeacherService teacherService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setId(10);
        when(teacherDao.findById(anyInt())).thenReturn(teacher);
        assertEquals(10, teacherService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        Teacher schoolTeacher = new Teacher();
        schoolTeacher.setId(10);
        when(teacherDao.findByUser(anyObject())).thenReturn(schoolTeacher);
        assertEquals(10, teacherService.findByUser(new User()).getId().intValue());
    }
}