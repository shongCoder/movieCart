package org.example.mf.member;

import lombok.Cleanup;
import org.example.mf.common.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

public enum MemberDAO {

    INSTANCE;

    public Optional<MemberVO> get(String word, String pw) throws Exception {

        String query = """
                    SELECT * FROM tbl_member
                    WHERE
                        (uid=? OR email=?)
                    AND\s
                        upw= ?
                    AND\s
                        delflag=false
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, word);
        ps.setString(2, word);
        ps.setString(3, pw);

        @Cleanup ResultSet rs = ps.executeQuery();

        // 쿼리는 문제 없는데 값이 틀린다던지 문제가 생기는 경우
        if( !rs.next() ) {
            return Optional.empty();
        }
        MemberVO member = MemberVO.builder()
                .mno(rs.getInt("mno"))
                .uid(rs.getString("uid"))
                .upw(rs.getString("upw"))
                .email(rs.getString("email"))
                .delFlag(rs.getBoolean("delflag"))
                .build();


        return Optional.of(member);
    }

    public boolean join(MemberVO vo) throws Exception {

        String sql = """
                INSERT INTO tbl_member(uid, upw, email)
                VALUES (?, ?, ?)
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(sql);

        ps.setString(1, vo.getUid());
        ps.setString(2, vo.getUpw());
        ps.setString(3, vo.getEmail());

        int count = ps.executeUpdate();

        return count == 1;
    }

    public boolean checkUser(String uid, String email) throws Exception {

        boolean checkResult = false;

        String query = """
                SELECT COUNT(*) FROM tbl_member
                WHERE uid = ? OR email = ?
                """;

        @Cleanup Connection con = ConnectionUtil.INSTANCE.getDs().getConnection();
        @Cleanup PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, uid);
        ps.setString(2, email);

        @Cleanup ResultSet rs = ps.executeQuery();
        if( rs.next() ) {
            int count = rs.getInt(1);
            if( count > 0 ) {
                checkResult = true;
            }
        }

        return checkResult;
    }

}
