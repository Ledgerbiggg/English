package com.ledger.es_test1.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class CaptchaController {

    @GetMapping("/getCaptchaCode")
    public void getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(120, 60, 4, 600);
        String code = circleCaptcha.getCode();
        log.error(code);
        request.getSession().setAttribute("code",code);
        response.setContentType("image/jpeg");
        ImageIO.write(circleCaptcha.getImage(),"jpeg",response.getOutputStream());
    }
}
