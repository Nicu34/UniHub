package com.websystique.springmvc.converter;

import com.websystique.springmvc.dto.NewUserDto;
import com.websystique.springmvc.model.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by nicu on 6/5/2017.
 */
public class NewUserDtoToUser implements Converter<NewUserDto, User> {
    @Override
    public User convert(NewUserDto source) {
        User user = new User();

        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());
        user.setPhotoLink(source.getPhotoLink());
        user.setPassword(source.getPassword());
        user.setPhone(source.getPhone());
        user.setEmail(source.getEmail());
        user.setSsoId(source.getSsoId());
        user.setProfileEnum(source.getProfileEnum());

        return user;
    }
}
