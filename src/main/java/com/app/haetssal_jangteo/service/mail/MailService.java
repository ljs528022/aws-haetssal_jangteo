package com.app.haetssal_jangteo.service.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

//    메일전송이니까 매개변수는 메일주소
    public void sendMail(String mail, HttpServletResponse response) {
        String receiver = mail;
        String sender = "dev.sokkomann@gmail.com";
        String title = "햇살장터 이메일 인증을 완료 해주세요.";

        String code = createCode();
        Cookie cookie = new Cookie("code", code);

        cookie.setPath("/");
        cookie.setMaxAge(60 * 3); // 3분 후 코드 만료
        response.addCookie(cookie);

//        햇살장터가 할거는 랜덤코드발송
//        화면에서 해당코드 입력해서 인증하기

//        수업때할거는 링크보내서 인증완료링크보내는거임(body.append("<a href=\"\">");)

//        1.자바에서 코드 생성
//        2.쿠키에 저장
//        3.메일전송(코드)
//        4.링크클릭(코드)
//        5.컨트롤러에서 쿠키의 코드와 받은코드 검사
//        6-1.같으면 인증성공
//        6-2.다르면 인증실패
//        6-3.쿠키에 코드가없으면 인증만료
//        쿠키밸류와 파라미터로 받은 코드랑 대조
//        쿠키에 코드가없으면 제한시간이 지난거임
//        우리가 쿠키에 제한시간을 설정
//        단, 인증성공했을때 조심할게 인증성공이되면 쿠키 지워야함 - 해커가 가로챌수 있으니까.

        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<h2>회원가입 이메일 인증 코드입니다.</h1>");
        body.append("<h1>아래의 인증코드를 입력해주세요.<br>");
        body.append("?code=").append(code).append("\"인증하기</h1>");
        body.append("<h2>아래의 인증코드는 3분 뒤 만료 됩니다.</h1>");
//       cid:icon(애드인라인) 이렇게 쓰는게 규칙이래요
        body.append("<img src=\"cid:icon\">");
        body.append("</body></html>");

       try{
           MimeMessage mimeMessage = javaMailSender.createMimeMessage();
           MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
           mimeMessageHelper.setFrom(sender);
           mimeMessageHelper.setTo(receiver);
           mimeMessageHelper.setSubject(title);
//           setText에서 boolean을 true로 해야 전송할때 문자열(html태그들이 그대로 문자열로 인식)이 아니라 html로 감
           mimeMessageHelper.setText(body.toString(), true);

           FileSystemResource fileSystemResource = new FileSystemResource(new File(
                   "D:\\spring\\workspace\\haetssal_jangteo" +
                   "\\src\\main\\resources\\static\\images\\haetssal-logo.png"));
           mimeMessageHelper.addInline("icon", fileSystemResource);

           javaMailSender.send(mimeMessage);

//           애드인라인, 애드어태치는 파일보내는거임 우리가 가지고있는 이미지를 파일에 보내는거임
//           그러려면 파일시스템리소스가 필요
//           애드인라인은 내용에 이미지를 넣을거냐ㅑ
       } catch (MessagingException e) {
           e.printStackTrace();;
       }
    }
//    1.인증번호생성
    private String createCode() {
        String codes = "0123456789";
        String code = "";
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            code += codes.charAt(random.nextInt(codes.length()));
        }
        return code;
    }
}
