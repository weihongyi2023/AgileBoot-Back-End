package com.agileboot.domain.system.user.command;

import com.agileboot.common.annotation.ExcelColumn;
import lombok.Data;
import org.springframework.data.annotation.Transient;

/**
 * @author valarchie
 */
@Data
public class AddUserCommand {

    @ExcelColumn(name = "部门ID")
    private Long deptId;

    @ExcelColumn(name = "用户名")
    private String username;

    @ExcelColumn(name = "昵称")
    private String nickName;

    @ExcelColumn(name = "邮件")
    private String email;

    @ExcelColumn(name = "电话号码")
    private String phoneNumber;

    @ExcelColumn(name = "性别")
    private Integer sex;

    @ExcelColumn(name = "头像")
    private String avatar;

    @ExcelColumn(name = "密码")
    private String password;

    @ExcelColumn(name = "状态")
    private String status;

    @ExcelColumn(name = "角色ID")
    private Long roleId;

    @ExcelColumn(name = "职位ID")
    private Long postId;

    @ExcelColumn(name = "备注")
    private String remark;

    /**
     * 验证码
     */
    @Transient
    private String code;

    /**
     * 唯一标识
     */
    @Transient
    private String uuid;
}
