package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.InvitedUserDao;
import com.websystique.springmvc.model.InvitedUser;
import com.websystique.springmvc.model.ProfileEnum;
import com.websystique.springmvc.model.SchoolGroup;
import com.websystique.springmvc.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nicu on 6/5/2017.
 */
@Service
@Transactional
public class InvitedUserService {

    @Autowired
    private InvitedUserDao invitedUserDao;

    public InvitedUser findById(int id) {
        return invitedUserDao.findById(id);
    }

    public InvitedUser findBySsoId(String ssoId) {
        return invitedUserDao.findBySsoId(ssoId);
    }

    public void save(InvitedUser student) {
        invitedUserDao.save(student);
    }

    public void deleteByEmail(String email) {
        invitedUserDao.deleteByEmail(email);
    }

    public List<InvitedUser> findAll() {
        return invitedUserDao.findAllInvitedUsers();
    }
    /**
     * Saves multiple temporary accounts.
     * @param invitedEmails the invited emails accounts
     * @param profileEnum the profile type which the emails was invited for
     * @param university the university which the accounts are belonging
     * @param schoolGroup the group which possible students accounts
     */
    public void saveMultipleAccounts(String invitedEmails, ProfileEnum profileEnum, University university, SchoolGroup schoolGroup) {
        Arrays.stream(invitedEmails.trim()
                .replaceAll("\\s+", " ")
                .split(" "))
                .map(email -> buildInvitedUser(email, university, profileEnum, schoolGroup))
                .forEach(this::save);
    }

    /**
     * Builds the invited user details
     * @param email given email for invited user
     * @param university given university for invited user
     * @param profileEnum given profile type for invited user
     * @param schoolGroup given school group for possible invited student
     * @return
     */
    private InvitedUser buildInvitedUser(String email, University university, ProfileEnum profileEnum, SchoolGroup schoolGroup) {
        String userName = email.substring(0, email.indexOf("@"));
        InvitedUser invitedUser = new InvitedUser();

        invitedUser.setEmail(email);
        invitedUser.setProfileEnum(profileEnum);
        invitedUser.setSsoId(userName);
        invitedUser.setUniversity(university);
        invitedUser.setSchoolGroup(schoolGroup);

        return invitedUser;
    }
}
