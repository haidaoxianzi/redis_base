package cn.demo.caffeine.service;

import cn.demo.caffeine.bean.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * User对应的Service接口
 * 要使用MyBatisPlus的Service完成CRUD操作，得继承IService
 */
public interface IUserService extends IService<User> {

}
