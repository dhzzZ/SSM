package tl.mapper;

import org.apache.ibatis.annotations.*;
import tl.entity.Users;

import java.util.List;

public interface UsersMapper {

    public List<Users> findAllUsersAndOrdersAndOrderdetailAndCommodityLazy(Integer id);

    @Select("select * from users where id=#{id}")
    public Users findUsersById(Integer id);

    /**
     * 登录
     * @param users
     * @return
     */
    @Select("select * from users where name=#{name} and password=#{password}")
    public Users login(Users users);

    /**
     * 添加users
     * @param users
     * @return
     */
    @SelectKey(statement = "select users_seq.nextval from dual",before = true,resultType = int.class,keyProperty = "id")
    @Insert("insert into users values(#{id},#{name},#{password},#{sex},#{age},#{birthday})")
    public int addUsers(Users users);

    /**
     * 删除
     * @param id
     * @return
     */
    @Delete("delete from users where id=#{id}")
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
    @Select("select * from users")
    public List<Users> findAllUsers();
}
