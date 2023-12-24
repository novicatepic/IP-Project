package org.unibl.etf.ip.backend.controller;

import org.javalite.activeweb.AppController;
import org.javalite.activeweb.Captcha;

import java.io.IOException;

public class CaptchaController extends AppController {

    public void index() throws IOException {
            String captchaText = Captcha.generateText();
            session("captcha", captchaText);
            outputStream("image/png").write(Captcha.generateImage(captchaText));
        }
}
