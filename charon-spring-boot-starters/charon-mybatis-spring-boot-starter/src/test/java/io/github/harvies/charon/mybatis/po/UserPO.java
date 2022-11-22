package io.github.harvies.charon.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.harvies.charon.mybatis.diff.BasePlusPO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author harvies
 */
@Data
//链式调用
@Accessors(chain = true)
@TableName(value = "user", autoResultMap = true)
public class UserPO extends BasePlusPO implements Serializable {
    private static final long serialVersionUID = -4721607536018568393L;
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
