package com.agileboot.domain.system.user.model;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.agileboot.common.config.AgileBootConfig;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.exception.error.ErrorCode.Business;
import com.agileboot.domain.system.config.model.ConfigModel;
import com.agileboot.domain.system.config.model.ConfigModelFactory;
import com.agileboot.domain.system.dept.model.DeptModelFactory;
import com.agileboot.domain.system.post.model.PostModelFactory;
import com.agileboot.domain.system.role.model.RoleModel;
import com.agileboot.domain.system.role.model.RoleModelFactory;
import com.agileboot.domain.system.user.command.AddUserCommand;
import com.agileboot.domain.system.user.command.UpdateProfileCommand;
import com.agileboot.domain.system.user.command.UpdateUserCommand;
import com.agileboot.domain.system.user.command.UpdateUserPasswordCommand;
import com.agileboot.infrastructure.security.AuthenticationUtils;
import com.agileboot.infrastructure.web.domain.login.LoginUser;
import com.agileboot.orm.system.entity.SysUserEntity;
import com.agileboot.orm.system.service.ISysUserService;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Optional;

/**
 * @author valarchie
 */
@Data
@NoArgsConstructor
public class UserModel extends SysUserEntity {

    private ISysUserService userService;

    private PostModelFactory postModelFactory;

    private DeptModelFactory deptModelFactory;

    private RoleModelFactory roleModelFactory;

    private ConfigModelFactory configModelFactory;


    public UserModel(SysUserEntity entity, ISysUserService userService, PostModelFactory postModelFactory,
        DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory) {
        this(userService, postModelFactory, deptModelFactory, roleModelFactory);

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public UserModel(SysUserEntity entity, ISysUserService userService, PostModelFactory postModelFactory,
                     DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory,ConfigModelFactory configModelFactory) {
        this(userService, postModelFactory, deptModelFactory, roleModelFactory);
        this.configModelFactory = configModelFactory;

        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public UserModel(ISysUserService userService, PostModelFactory postModelFactory,
        DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory) {
        this.userService = userService;
        this.postModelFactory = postModelFactory;
        this.deptModelFactory = deptModelFactory;
        this.roleModelFactory = roleModelFactory;
    }

    public UserModel(ISysUserService userService, PostModelFactory postModelFactory,
                     DeptModelFactory deptModelFactory, RoleModelFactory roleModelFactory,ConfigModelFactory configModelFactory) {
        this.userService = userService;
        this.postModelFactory = postModelFactory;
        this.deptModelFactory = deptModelFactory;
        this.roleModelFactory = roleModelFactory;
        this.configModelFactory = configModelFactory;
    }

    public void loadAddUserCommand(AddUserCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "userId");
        }
    }


    public void loadUpdateUserCommand(UpdateUserCommand command) {
        if (command != null) {
            loadAddUserCommand(command);
        }
    }

    public void loadUpdateProfileCommand(UpdateProfileCommand command) {
        if (command != null) {
            this.setSex(command.getSex());
            this.setNickName(command.getNickName());
            this.setPhoneNumber(command.getPhoneNumber());
            this.setEmail(command.getEmail());
        }
    }


    public void checkUsernameIsUnique() {
        if (userService.isUserNameDuplicated(getUsername())) {
            throw new ApiException(ErrorCode.Business.USER_NAME_IS_NOT_UNIQUE);
        }
    }

    public void checkPhoneNumberIsUnique() {
        if (StrUtil.isNotEmpty(getPhoneNumber()) && userService.isPhoneDuplicated(getPhoneNumber(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_PHONE_NUMBER_IS_NOT_UNIQUE);
        }
    }

    public void checkFieldRelatedEntityExist() {
        if (getPostId() != null) {
            postModelFactory.loadById(getPostId());
        }

        if (getDeptId() != null) {
            deptModelFactory.loadById(getDeptId());
        }

        if (getRoleId() != null) {
            roleModelFactory.loadById(getRoleId());
        }

    }


    public void checkEmailIsUnique() {
        if (StrUtil.isNotEmpty(getEmail()) && userService.isEmailDuplicated(getEmail(), getUserId())) {
            throw new ApiException(ErrorCode.Business.USER_EMAIL_IS_NOT_UNIQUE);
        }
    }

    public void checkCanBeDelete(LoginUser loginUser) {
        if (Objects.equals(getUserId(), loginUser.getUserId())
            || this.getIsAdmin()) {
            throw new ApiException(ErrorCode.Business.USER_CURRENT_USER_CAN_NOT_BE_DELETE);
        }
    }


    public void modifyPassword(UpdateUserPasswordCommand command) {
        if (!AuthenticationUtils.matchesPassword(command.getOldPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_PASSWORD_IS_NOT_CORRECT);
        }

        if (AuthenticationUtils.matchesPassword(command.getNewPassword(), getPassword())) {
            throw new ApiException(ErrorCode.Business.USER_NEW_PASSWORD_IS_THE_SAME_AS_OLD);
        }
        setPassword(AuthenticationUtils.encryptPassword(command.getNewPassword()));
    }

    public void resetPassword(String newPassword) {
        setPassword(AuthenticationUtils.encryptPassword(newPassword));
    }

    @Override
    public boolean updateById() {
        if (this.getIsAdmin() && AgileBootConfig.isDemoEnabled()) {
            throw new ApiException(Business.USER_ADMIN_CAN_NOT_BE_MODIFY);
        }

       return super.updateById();
    }

    public void setRegisterUserDefaultRole() {
        ConfigModel config = configModelFactory.loadByConfigKey("sys.register.user.default.role");
        String configKey = Optional.ofNullable(config.getConfigValue()).orElse("0");
        long roleId = Long.parseLong(configKey);
        RoleModel roleModel = roleModelFactory.loadById(roleId);
        if (Objects.isNull(roleModel)){
            throw new ApiException(Business.USER_REGISTER_DEFAULT_ROLE_UNSET);
        }
        setRoleId(roleId);
    }

}
