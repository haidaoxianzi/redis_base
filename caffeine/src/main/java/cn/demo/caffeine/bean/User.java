package cn.demo.caffeine.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.ToString;

/**
 * @TableName 标识实体类对应的表名
 */
@ToString
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}

