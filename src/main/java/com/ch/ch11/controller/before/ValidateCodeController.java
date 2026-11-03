package com.ch.ch11.controller.before;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Controller
public class ValidateCodeController {

    // 画布宽高
    private static final int WIDTH = 100;
    private static final int HEIGHT = 40;
    // 验证码字符，去掉容易混淆的0、O、1、I
    private static final String CODE_SOURCE = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private final Random random = new Random();

    @GetMapping("/validateCode")
    public void createCode(HttpSession session, HttpServletResponse response) throws IOException {
        // 1. 创建图片缓冲区
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();

        // 2. 设置背景色
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);

        // 3. 画干扰线
        for (int i = 0; i < 8; i++) {
            graphics.setColor(getRandomColor());
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            graphics.drawLine(x1, y1, x2, y2);
        }

        // 4. 生成4位验证码
        StringBuilder code = new StringBuilder();
        graphics.setFont(new Font("Arial", Font.BOLD, 24));
        for (int i = 0; i < 4; i++) {
            int index = random.nextInt(CODE_SOURCE.length());
            char c = CODE_SOURCE.charAt(index);
            code.append(c);
            graphics.setColor(getRandomColor());
            graphics.drawString(String.valueOf(c), 10 + i * 22, 28);
        }

        // 5. 验证码存入session，登录接口用来比对
        session.setAttribute("validateCode", code.toString());

        graphics.dispose();

        // 6. 设置响应，禁止缓存，输出图片
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    // 获取随机颜色
    private Color getRandomColor() {
        int r = random.nextInt(200);
        int g = random.nextInt(200);
        int b = random.nextInt(200);
        return new Color(r, g, b);
    }
}
