package cn.janine.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import cn.janine.util.Captcha;


/**
 * 登录Controller
 * 
 *
 */
@Controller
public class LoginController {
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private Captcha captcha;

    /**
     * 跳转至登录页面
     * 
     * @return 登录页面地址
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "index";
    }

    
    /**
	 * 获取验证码代码
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "code/captcha-image", method = RequestMethod.GET)
	public void captchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		captcha.getKaptchaImage(request, response);
	}

	/**
	 * 验证码检验
	 * @param verifyCode
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "code/captcha-code", method = RequestMethod.POST)
	public void captchaCode(@RequestParam("verifyCode") String verifyCode, HttpServletRequest request, HttpServletResponse response) {
		captcha.getKaptchaCode(verifyCode, request, response);
	}
}
