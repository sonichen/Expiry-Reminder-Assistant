package com.cyj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cyj.pojo.User;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 修改密码
     */
    int  updatePwd(long userId, String newPsd);
    /**
     * 校验手机号码和密码
     * @param tel
     * @param password
     * @return
     */
    User queryUserByTelAndPassword(String tel, String password);

    User checkRegister(String tel);
}
