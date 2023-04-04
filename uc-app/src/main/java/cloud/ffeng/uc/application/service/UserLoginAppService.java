package cloud.ffeng.uc.application.service;

import cloud.ffeng.uc.facade.dto.UserLoginByAuthDTO;
import cloud.ffeng.uc.facade.dto.UserLoginDTO;
import cloud.ffeng.uc.facade.request.UserLoginByAuthRequest;
import cloud.ffeng.uc.facade.request.UserLoginRequest;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * 用户登录应用服务
 * <p>
 * 主要功能：
 * <ul>
 *  <li>普通页面登录：账号密码登录逻辑</li>
 *  <li>三方授权登录：OAuth2授权回调登录</li>
 *  <li>扫二维码登录：内部应用登录态扫码登录</li>
 * <ul/>
 *
 * @author feng
 */
@Validated
public interface UserLoginAppService {

    /**
     * 用户中心 --普通页面登录：账号密码 + 校验码逻辑 <p>
     * 登录账号：兼容邮箱、手机号、用户名，任意一个关键信息匹配用户。
     *
     * @param request 登录请求
     * @return 登录结果
     */
    UserLoginDTO login(@Valid UserLoginRequest request);

    /**
     * 通过第三方授权登录： OAuth2授权码模式
     * 注意：此登录方式如果用户不存在，会默认创建新用户。
     *
     * @param request 请求
     * @return 登录结果
     */
    UserLoginByAuthDTO loginByAuth(@Valid UserLoginByAuthRequest request);

}
