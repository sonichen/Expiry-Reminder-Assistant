package com.cyj.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cyj.dto.user.UserDto;
import com.cyj.dto.user.WxUser;
import com.cyj.mapper.CategoriesMapper;
import com.cyj.mapper.UserMapper;
import com.cyj.pojo.Categories;
import com.cyj.pojo.User;
import com.cyj.service.user.UserService;
import com.cyj.utils.TokenUtil;
import com.cyj.utils.result.JsonObject;
import com.cyj.utils.secret.Encrypt;
import com.cyj.utils.secret.MobileUtil;
import com.cyj.utils.secret.SnowflakeIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cyj
 * @since 2022-04-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    CategoriesMapper categoriesMapper;

    /**
     * 更新密码
     * @param userId
     * @param oldPwd
     * @param newPwd
     * @param checkNewPwd
     * @return
     */
    @Override
    public int updatePwd(long userId, String oldPwd, String newPwd, String checkNewPwd) {
        if(checkNewPwd!=newPwd){
            return 1;
        }else{
            return userMapper.updatePwd(userId,newPwd);
        }
    }

    /**
     * 通过手机号码和密码登录
     * @param username
     * @param tel
     * @return
     */
    @Override
    public User queryUserByTelAndPassword(String username, String tel) {
        return userMapper.queryUserByTelAndPassword(username,tel);
    }

    /**
     * 查询用户列表
     * @param page
     * @param pageCount
     * @return
     */
    @Override
    public JsonObject<User> findListByPage(Integer page, Integer pageCount){
        JsonObject<User> jsonObject =new JsonObject<>();
        IPage<User> wherePage = new Page<>(page, pageCount);
        userMapper.selectPage(wherePage,null);
        jsonObject.setData(wherePage.getRecords());
        jsonObject.setCount(wherePage.getTotal());
        return jsonObject;
    }

    /**
     * 增加
     * @param user
     * @return
     */
    @Override
    public int add(User user) throws Exception {

        User check=userMapper.checkRegister(user.getTel());
        if(check!=null){
            throw new Exception("该用户已注册");
        }
        user.setAvatar("http://8.130.8.164:8080/img/infuzhou/user/968544816357052416.jpg");
        user.setIntroduce("我的签名");
        //            设置唯一的id
        long n = (long)(Math.random() % 29) + 1;
        long n1 = (long)(Math.random() % 29) + 1;
        user.setUsername((new SnowflakeIdUtils(n,n1).nextId())+"");

        return baseMapper.insert(user);
    }

    public int addSystemCategories(Long userId){

        categoriesMapper.insert(new Categories(userId,"药品","http://8.130.8.164:8080/img/re/resource/medicine.png","1"));
        categoriesMapper.insert(new Categories(userId,"食品","http://8.130.8.164:8080/img/re/resource/food.png","1"));
        categoriesMapper.insert(new Categories(userId,"化妆品","http://8.130.8.164:8080/img/re/resource/cosmetics.png","1"));
        categoriesMapper.insert(new Categories(userId,"饮品","http://8.130.8.164:8080/img/re/resource/drink.png","1"));
        categoriesMapper.insert(new Categories(userId,"运动","http://8.130.8.164:8080/img/re/resource/sports.png","1"));
        return 0;
    }
    @Autowired
    private UserService userService;
    @Override
    public int addWxUser(User user) throws Exception {
        System.out.println("开始保存="+user);
        user.setAvatar(user.getAvatar());
        user.setPassword(Encrypt.md5AndSha("123456"));
        user.setIntroduce("我的签名");
        user.setUsername(user.getUsername());
        user.setSex(0l);
        user.setWxOpenid(user.getWxOpenid());
        user.setId(0l);
        user.setTel("1372862012");
        System.out.println("保存微信用户="+user);
        int result=  baseMapper.insert(user);
        User check=userService.findUserByWxOpenId(user.getWxOpenid());
        addSystemCategories(check.getId());
        return result;
    }


    /**
     * 删除
     * @param id 主键
     * @return
     */
    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    /**
     * 更新数据
     * @param user
     * @return
     */
    @Override
    public int updateData(UserDto user) throws Exception {
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("tel",user.getTel());
        User check=baseMapper.selectOne(wrapper);
//        if(check!=null){
//            throw new Exception("该手机号码已经注册过!");
//        }
        if(!MobileUtil.checkPhone(user.getTel())){
           throw new Exception("手机号码格式不正确!");
        }
        User newUser=  new User();
        newUser.setId(user.getId());

        newUser.setUsername(user.getUsername());
        newUser.setSex(user.getSex());
        newUser.setTel(user.getTel());
        newUser.setCreateTime(user.getCreateTime());
        newUser.setIntroduce(user.getIntroduce());
        return baseMapper.updateById(newUser);
    }

    @Override
    public UserDto findById(Long id){
        User user=  baseMapper.selectById(id);
        UserDto userDto=new UserDto();
        userDto.setId(id);
        userDto.setUsername(user.getUsername());
        userDto.setSex(user.getSex());
        userDto.setTel(user.getTel());
        userDto.setCreateTime(user.getCreateTime());
        userDto.setIntroduce(user.getIntroduce());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
    @Override
    public User findUser(Long id){
       return baseMapper.selectById(id);
    }

    @Override
    public User findUserByWxOpenId(String openId) {
        System.out.println("查询数据库="+openId);
        QueryWrapper wrapper=new QueryWrapper();
        wrapper.eq("wxOpenid",openId);
        User user=baseMapper.selectOne(wrapper);
        System.out.println("数据库结构="+user);
        WxUser user1=new WxUser();
        if(user==null){
            return null;
        }
        user1.setId(user.getId());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());
        user1.setWxOpenId(openId);
        return user;
    }


}
