package com.hrh.mall.controller;

import com.google.common.collect.Maps;
import com.hrh.mall.common.api.CommonPage;
import com.hrh.mall.common.api.CommonResult;
import com.hrh.mall.common.json.MapperUtils;
import com.hrh.mall.common.okhttp.OkHttpClientUtil;
import com.hrh.mall.domain.UmsAdmin;
import com.hrh.mall.domain.UmsRole;
import com.hrh.mall.dto.LoginInfo;
import com.hrh.mall.dto.ResponseResult;
import com.hrh.mall.dto.UmsAdminLoginParam;
import com.hrh.mall.service.UmsAdminService;
import com.hrh.mall.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: mall-monomer
 * @Package: com.hrh.mall.controller
 * @ClassName: UserController
 * @Author: HuRonghua
 * @Description: 用户接口
 * @Date: 2020/4/25 15:41
 * @Version: 1.0
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    private static final String URL_OAUTH_TOKEN = "http://localhost:8888/oauth/token";

    @Value("${business.oauth2.grant_type}")
    public String oauth2GrantType;

    @Value("${business.oauth2.client_id}")
    public String oauth2ClientId;

    @Value("${business.oauth2.client_secret}")
    public String oauth2ClientSecret;

    @Resource(name = "userDetailsService")
    private UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Resource(name = "tokenStore")
    public TokenStore tokenStore;

    @Resource
    private UmsAdminService umsAdminService;

    @Resource
    private UmsRoleService umsRoleService;
    @Resource
    private UmsAdminService adminService;

    @Resource
    private UmsRoleService roleService;

    @ApiOperation("用户登录登录")
    @PostMapping("/login")
    public ResponseResult<Map<String,Object>> login(@RequestBody UmsAdminLoginParam umsAdminLoginParam){
        // 验证账号密码
        UserDetails userDetails = userDetailsService.loadUserByUsername(umsAdminLoginParam.getUsername());
        // 登陆失败
        if(userDetails == null ||  !bCryptPasswordEncoder.matches(umsAdminLoginParam.getPassword(),userDetails.getPassword())) {
            return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.FAIL,"账号或者密码错误",null);
        }
        // 封装返回的结果集
        Map<String, Object> result = Maps.newHashMap();
        Map<String, String> params = new HashMap<>();
        params.put("username", umsAdminLoginParam.getUsername());
        params.put("password", umsAdminLoginParam.getPassword());
        params.put("grant_type", oauth2GrantType);
        params.put("client_id", oauth2ClientId);
        params.put("client_secret", oauth2ClientSecret);

        try {
            Response response = OkHttpClientUtil.getInstance().postData(URL_OAUTH_TOKEN, params);
            String jsonString = response.body().string();
            Map<String, Object> jsonMap = MapperUtils.json2map(jsonString);
            String token = String.valueOf(jsonMap.get("access_token"));
            result.put("token", token);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseResult<Map<String, Object>>(ResponseResult.CodeStatus.OK, "登录成功",result);
    }

    /**
     * 获取用户信息
     * @param httpServletRequest {@link HttpServletRequest}
     * @return
     */
    @GetMapping("/info")
    public ResponseResult<LoginInfo> info(HttpServletRequest httpServletRequest, Principal principal){
        // 获取用户名
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name = authentication.getName();

        LoginInfo loginInfo = null;
        UmsAdmin umsAdmin = umsAdminService.get(name);
        if(umsAdmin != null){
            loginInfo = new LoginInfo();
            loginInfo.setId(umsAdmin.getId());
            loginInfo.setNickName(umsAdmin.getNickName());
            loginInfo.setName(umsAdmin.getUsername());
            loginInfo.setAvatar(umsAdmin.getIcon());
            loginInfo.setRoles(new String[]{"test"});
            loginInfo.setMenus(roleService.getMenuList(umsAdmin.getId()));
        }
        else{
            return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.FAIL);
        }
        return new ResponseResult<LoginInfo>(ResponseResult.CodeStatus.OK,"获取用户信息",loginInfo);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ResponseResult<Void> logOut(HttpServletRequest httpServletRequest){
        String access_token = httpServletRequest.getParameter("access_token");
        OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(access_token);
        tokenStore.removeAccessToken(oAuth2AccessToken);
        return new ResponseResult<Void>(ResponseResult.CodeStatus.OK,"退出登录",null);
    }

    @ApiOperation("获取所有角色")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<List<UmsRole>> listAll() {
        List<UmsRole> list = umsRoleService.list();
        return new ResponseResult<>(ResponseResult.CodeStatus.OK,"获取成功",list);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                     @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                     @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return new ResponseResult<CommonPage<UmsAdmin>>(ResponseResult.CodeStatus.OK,"获取成功",CommonPage.restPage(adminList));
    }

}
