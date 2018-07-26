package tl.service;


import tl.entity.Users;

import java.util.List;

public interface UsersService {
    /**
     * 根据id查找
     * @param id
     * @return
     */
    public Users findUsersById(Integer id);

    /**
     * 登录
     * @param users
     * @return
     */
    public Users login(Users users);

    /**
     * 添加users
     * @param users
     * @return
     */

    public int addUsers(Users users);

    /**
     * 删除
     * @param id
     * @return
     */

    public int delUsers(Integer id);

    /**
     * 添加
     * @param users
     * @return
     */
    public int updateUsers(Users users);

    /**
     * 展示所有users
     * @return
     */
    public List<Users> findAllUsers();
}
