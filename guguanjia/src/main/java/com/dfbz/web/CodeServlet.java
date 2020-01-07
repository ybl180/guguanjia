package com.dfbz.web;


import com.google.code.kaptcha.servlet.KaptchaServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author ybl
 * @version 1.0.1
 * @conpany 东风标准
 * @date 2020/1/7 14:41
 * @desciption
 */
@WebServlet(urlPatterns = "/code.jpg", initParams = {
        @WebInitParam(name = "kaptcha.image.width", value = "120"),
        @WebInitParam(name = "kaptcha.image.height", value = "32"),
        @WebInitParam(name = "kaptcha.textproducer.char.length", value = "4"),
        @WebInitParam(name = "kaptcha.textproducer.font.color", value = "black"),
        @WebInitParam(name = "kaptcha.background.clear.from", value = "white"),
        @WebInitParam(name = "kaptcha.background.clear.to", value = "blue"),
//        @WebInitParam(name = "kaptcha.noise.color", value = "black"),
        @WebInitParam(name = "kaptcha.session.key", value = "vcode"),
        @WebInitParam(name = "kaptcha.textproducer.font.size", value = "28"),
        @WebInitParam(name = "kaptcha.textproducer.char.string", value = "1234567890"),
//        @WebInitParam(name = "kaptcha.textproducer.font.names", value = "微软雅黑"),
//        @WebInitParam(name = "kaptcha.obscurificator.impl", value = "com.google.code.kaptcha.impl.ShadowGimpy")
})
public class CodeServlet extends KaptchaServlet {

}
