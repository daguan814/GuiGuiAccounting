package org.shuijing.guiguijizhang.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2022-12-01
 */
@Getter
@Setter
@TableName("code_user")
@ApiModel(value = "CodeUser对象", description = "")
public class CodeUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("pwdcode")
    private String pwdcode;

    @TableField("iemi")
    private String iemi;
}
