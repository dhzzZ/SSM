package tl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tl.entity.Users;
import tl.mapper.UsersMapper;
import tl.service.UsersService;

import java.util.List;

@Service("usersService")
@Transactional
public class UsersServiceImpl implements UsersService {
    @Autowired
    UsersMapper usersMapper;
    @Override
    public Users findUsersById(Integer id)
    {
        return usersMapper.findUsersById(id);
    }

    @Override
    public Users login(Users users) {
        return usersMapper.login(users);
    }

    @Override
    public int addUsers(Users users) {
        return usersMapper.addUsers(users);
    }

    @Override
    public int delUsers(Integer id) {
        return usersMapper.delUsers(id);
    }

    @Override
    public int updateUsers(Users users) {
        return usersMapper.updateUsers(users);
    }

    @Override
    public List<Users> findAllUsers() {
        return usersMapper.findAllUsers();
    }
}
