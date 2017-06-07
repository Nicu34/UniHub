package com.test;

import com.websystique.springmvc.dao.StudentDao;
import com.websystique.springmvc.model.Student;
import com.websystique.springmvc.model.User;
import com.websystique.springmvc.service.StudentService;
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
public class StudentServiceTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    @Before
    public void init() {
        initMocks(this);
    }
    @Test
    public void findById() throws Exception {
        Student student = new Student();
        student.setId(10);
        when(studentDao.findById(anyInt())).thenReturn(student);
        assertEquals(10, studentService.findById(10).getId().intValue());
    }

    @Test
    public void findByShortName() throws Exception {
        Student schoolStudent = new Student();
        schoolStudent.setId(10);
        when(studentDao.findByUser(anyObject())).thenReturn(schoolStudent);
        assertEquals(10, studentService.findByUser(new User()).getId().intValue());
    }
}