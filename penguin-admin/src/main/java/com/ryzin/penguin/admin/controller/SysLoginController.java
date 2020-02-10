package com.ryzin.penguin.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ryzin.penguin.admin.model.SysUser;
import com.ryzin.penguin.admin.model.SysUserToken;
import com.ryzin.penguin.admin.service.SysUserService;
import com.ryzin.penguin.admin.service.SysUserTokenService;
import com.ryzin.penguin.admin.vo.LoginBean;
import com.ryzin.penguin.core.http.HttpResult;

@RestController
public class SysLoginController {

//	@Autowired
//	private Producer producer;
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserTokenService sysUserTokenService;

	@GetMapping("captcha.jpg")
	public void captcha(HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Cache-Control", "no-store, no-cache");
		response.setContentType("image/jpeg");

//		// 生成文字验证码
//		String text = producer.createText();
//		// 生成图片验证码
//		BufferedImage image = producer.createImage(text);
//		// 保存到shiro session
//		ShiroUtils.setSessionAttribute(KaptchaConstants.KAPTCHA_SESSION_KEY, text);
//
//		ServletOutputStream out = response.getOutputStream();
//		ImageIO.write(image, "jpg", out);
//		IOUtils.closeQuietly(out);
	}

	/**
	 * 登录
	 */
	@PostMapping(value = "/sys/login")
	public HttpResult login(@RequestBody LoginBean loginBean) throws IOException {
		String captcha = loginBean.getCaptcha();
		String username = loginBean.getUsername();
		String password = loginBean.getPassword();

//		String kaptcha = ShiroUtils.getKaptcha(KaptchaConstants.KAPTCHA_SESSION_KEY);
//		if (!captcha.equalsIgnoreCase(kaptcha)) {
//			return HttpResult.error("验证码不正确");
//		}

		// 用户信息
		SysUser user = sysUserService.findByUserName(username);

		// 账号不存在、密码错误
		if (user == null) {
			return HttpResult.error("账号不存在");
		}
		
//		if (!match(user, password)) {
//			return HttpResult.error("密码不正确");
//		}

		// 账号锁定
		if (user.getStatus() == 0) {
			return HttpResult.error("账号已被锁定,请联系管理员");
		}

		// 生成token，并保存到数据库
		SysUserToken data = sysUserTokenService.createToken(user.getUserId());
		return HttpResult.ok(data);
	}

//	public boolean match(SysUser user, String password) {
//		return user.getPassword().equals(PasswordUtils.encrypte(password, user.getSalt()));
//	}
}
