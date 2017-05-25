package com.websystique.springmvc.converter;

import com.websystique.springmvc.dto.AdminDto;
import com.websystique.springmvc.model.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by nicu on 5/26/2017.
 */
public class AdminDtoToUser implements Converter<AdminDto, User> {

    @Override
    public User convert(AdminDto source) {
        User user = new User();

        user.setPhone(source.getUserPhone());
        user.setLastName(source.getUserLastName());
        user.setEmail(source.getUserEmail());
        user.setPassword(source.getUserPassword());
        user.setSsoId(source.getUsername());
        user.setFirstName(source.getUserFirstName());

        return user;
    }
}
