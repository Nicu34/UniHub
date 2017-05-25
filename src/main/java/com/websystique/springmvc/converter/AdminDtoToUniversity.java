package com.websystique.springmvc.converter;

import com.websystique.springmvc.dto.AdminDto;
import com.websystique.springmvc.model.University;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by nicu on 5/26/2017.
 */
public class AdminDtoToUniversity implements Converter<AdminDto, University> {

    @Override
    public University convert(AdminDto source) {
        University university = new University();

        university.setShortName(source.getUniversityShortName());
        university.setLongName(source.getUniversityLongName());
        university.setAddress(source.getUniversityAddress());
        university.setCity(source.getUniversityCity());
        university.setPhone(source.getUniversityPhone());

        return university;
    }
}
