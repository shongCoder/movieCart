package org.example.mf.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/mypage")
@Log4j2
public class MypageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 쿠키의 배열 받아오기
        Cookie[] cks = req.getCookies();

        String ckResult = "null";

        // movies 쿠키 찾아서 value값을 할당
        for (Cookie ck : cks) {
            if(ck.getName().equals("movies")) {
                ckResult = ck.getValue();
            }
        }

        // & 기준으로 쪼개서 배열에 핟당
        String[] values = ckResult.split("&");
        List<MovieVO> movieCart = new ArrayList<>();

        // movies에 조건에 맞는 영화만 가져왔음
        // value를 파라미터로 전달하여 받은 데이터를 다시 리스트에 할당

        for(String value : values) {
            try {
                MovieVO movie = MovieDAO.INSTANCE.addCartMovies(value);
                if(movie != null) {
                    movieCart.add(movie);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        req.setAttribute("movieCart", movieCart);
        req.getRequestDispatcher("/WEB-INF/mypage.jsp").forward(req, resp);

        log.info(values);


    }
}
