package org.example.mf.member;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = "/signin")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/signin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String uid = req.getParameter("uid");
        String upw = req.getParameter("upw");

        log.info("-------------------------");
        log.info(uid);
        log.info(upw);


        //DB에서 해당 사용자 정보를 확인해서 사용자정보를 얻어온다.
        try {
            Optional<MemberVO> result = MemberDAO.INSTANCE.get(uid, upw);
            result.ifPresentOrElse( memberVO -> {
                // member라는 이름의 쿠키 생성
                Cookie loginCookie = new Cookie("member", uid);
                // '/'로 하게 되면 모든 경로에 쿠키가 다 적용
                loginCookie.setPath("/");
                // 하루 동안 쿠키 유지
                loginCookie.setMaxAge(60*60*24);
                // 쿠키를 응답에 추가
                resp.addCookie(loginCookie);

                log.info("signin success");

                try {
                    resp.sendRedirect("/movies");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } , () -> {
                try {
                    resp.sendRedirect("/signin");

                }catch(IOException e){
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}