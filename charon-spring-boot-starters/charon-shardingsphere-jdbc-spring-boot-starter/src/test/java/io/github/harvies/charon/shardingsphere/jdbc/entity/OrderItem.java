package io.github.harvies.charon.shardingsphere.jdbc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_order_item")
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    private long orderItemId;

    private long orderId;

    private int userId;

    private String status;
}
