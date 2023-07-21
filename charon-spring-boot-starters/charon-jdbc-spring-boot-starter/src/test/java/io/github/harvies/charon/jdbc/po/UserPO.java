package io.github.harvies.charon.jdbc.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("t_user")
public class UserPO  {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String nickname;

    private String mobile;

    private Date gmtCreate;

    private Date gmtModified;
}
