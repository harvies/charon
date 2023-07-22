package io.github.harvies.charon.jdbc.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

import java.util.Map;

@Data
@TableName(value = "t_user", autoResultMap = true)
public class UserPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String mobile;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> features;
}
