package org.example.mf.movie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/movies")
@Log4j2
public class MoviesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doGet Movies");

        try {
            List<MovieVO> movies = MovieDAO.INSTANCE.getMovies();

            req.setAttribute("movies", movies);

            req.getRequestDispatcher("/WEB-INF/movies.jsp").forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        log.info("doPost Movies");

        // 체크된 영화의 제목 데이터 가져오기
        String[] selectedOptions = req.getParameterValues("check");

        if (selectedOptions != null && selectedOptions.length > 0) {
            StringBuilder moviesBuilder = new StringBuilder();

            // 제목 데이터의 배열을 순회
            for (String selectedOption : selectedOptions) {
                // moviesBuilder 데이터가 들어가있다면 글자 사이 '&' 추가
                if (moviesBuilder.length() > 0) {
                    moviesBuilder.append("&");
                }
                moviesBuilder.append(selectedOption);
            }//emd for

            // 수정한 데이터를 문자열로 반환
            String movies = moviesBuilder.toString();
            log.info("Movies combined: " + movies);

            // 위에서 만든 문자열을 value에 담아 쿠키 생성
            Cookie moviesCookie = new Cookie("movies", movies);
            resp.addCookie(moviesCookie);
        } else {
            log.info("No movies selected.");
        }//end if else

        // mypage로 이동
        resp.sendRedirect("/mypage");
    }

}









