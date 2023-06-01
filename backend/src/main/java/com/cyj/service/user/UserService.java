package com.cyj.service.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cyj.dto.user.UserDto;
import com.cyj.pojo.User;
import com.cyj.utils.result.JsonObject;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
public interface UserService extends IService<User> {

    int updatePwd(long userId,String oldPwd,String newPwd,String checkNewPwd);
    /**
     * 登录
     * @param
     * @param tel
     * @return
     */
    User queryUserByTelAndPassword(String tel, String password);
    /**
     * 查询用户列表
     * @param page
     * @param pageCount
     * @return
     */
    JsonObject<User> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param user 
     * @return int
     */
    int add(User user) throws Exception;
    int addWxUser(User user) throws Exception;

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param user 
     * @return int
     */
    int updateData(UserDto user) throws Exception;

    /**
     * id查询数据
     *
     * @param id id
     * @return User
     */
    UserDto findById(Long id);
    User findUser(Long id);

    User findUserByWxOpenId(String openId);

}
