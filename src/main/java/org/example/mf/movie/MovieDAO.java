package org.example.mf.movie;

import com.zaxxer.hikari.pool.HikariProxyResultSet;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.example.mf.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public enum MovieDAO {
    INSTANCE;

    MovieDAO() {

    }

    public List<MovieVO> getMovies() throws Exception {

        log.info("getMovies");

        String query = "SELECT * FROM tbl_movies;";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        @Cleanup ResultSet rs = ps.executeQuery();

        List<MovieVO> movies = new ArrayList<>();

        while (rs.next()) {
            MovieVO movie = MovieVO.builder()
                    .mid(rs.getInt("mid"))
                    .mtitle(rs.getString("mtitle"))
                    .imgUrl(rs.getString("img_url"))
                    .build();
            movies.add(movie);
        }

        return movies;
    }

    public MovieVO addCartMovies(String title) throws Exception {

        log.info("addCartMovies");

        String sql = "SELECT * FROM tbl_movies WHERE mtitle=?";

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, title);

        @Cleanup ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return MovieVO.builder()
                    .mid(rs.getInt("mid"))
                    .mtitle(rs.getString("mtitle"))
                    .imgUrl(rs.getString("img_url"))
                    .build();
        } else {
            return null;
        }
    }
}
