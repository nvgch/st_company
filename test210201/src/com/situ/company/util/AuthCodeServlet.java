package com.situ.company.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AuthCodeServlet")
public class AuthCodeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
private static char[] chs= { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
private static Random r = new Random();
public static String name="authcode";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("获取验证码图片");
        BufferedImage image = new BufferedImage(75, 25, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        g.setColor(new Color(200, 200, 255));
        g.fillRect(0, 0, 75, 25);
        g.setFont(new Font("隶书", Font.BOLD, 15));
     
        StringBuffer sf = new StringBuffer();
        
        
        for (int i = 0; i < 4; i++) {
            int index = r.nextInt(chs.length);
            sf.append(chs[index]);
            g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
            g.drawString(chs[index] + "", 15 * i, 18);
        }
        req.getSession().setAttribute(name, sf.toString());
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

}
