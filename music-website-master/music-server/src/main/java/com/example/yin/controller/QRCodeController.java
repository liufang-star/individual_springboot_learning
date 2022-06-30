package com.example.yin.controller;

import com.example.yin.utils.QRCodeMusicUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 二维码链接跳转
 *
 * @author lf
 * @version 1.0
 * @date 2022年04月22日
 */
@Slf4j
@AllArgsConstructor
@RestController
public class QRCodeController {

    @GetMapping("/api/meet/v1/enroll/manage/qrCode")
    public void getQRCode(String codeContent, HttpServletResponse response) {
        log.info("codeContent=" + codeContent);
        try {
            /*
             * 调用工具类生成二维码并输出到输出流中
             */
            QRCodeMusicUtil.createCodeToOutputStream(codeContent, response.getOutputStream());
            log.info("成功生成二维码!");
        } catch (IOException e) {
            log.error("发生错误， 错误信息是：{}！", e.getMessage());
        }
    }
}
