package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {

    private final DataSource dataSource;

    // 생성자를 통한 DataSource 의존성 주입
    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 회원 정보 저장
    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 커넥션 획득
            pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); // PreparedStatement 생성

            pstmt.setString(1, member.getName()); // SQL 파라미터 설정

            pstmt.executeUpdate(); // SQL 실행

            rs = pstmt.getGeneratedKeys(); // 생성된 ID 조회

            if (rs.next()) {
                member.setId(rs.getLong(1)); // 생성된 ID를 회원 객체에 설정
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 리소스 해제
        }
    }

    // ID로 회원 조회
    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id =?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 커넥션 획득
            pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
            pstmt.setLong(1, id); // SQL 파라미터 설정

            rs = pstmt.executeQuery(); // SQL 실행

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id")); // 조회된 결과를 회원 객체에 설정
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 리소스 해제
        }
    }

    // 모든 회원 조회
    @Override
    public List<Member> findAll() {
        String sql = "select * from member";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 커넥션 획득
            pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
            rs = pstmt.executeQuery(); // SQL 실행
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id")); // 조회된 결과를 회원 객체에 설정
                member.setName(rs.getString("name"));
                members.add(member);
            }

            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 리소스 해제
        }
    }

    // 이름으로 회원 조회
    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection(); // 데이터베이스 커넥션 획득
            pstmt = conn.prepareStatement(sql); // PreparedStatement 생성
            pstmt.setString(1, name); // SQL 파라미터 설정

            rs = pstmt.executeQuery(); // SQL 실행

            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id")); // 조회된 결과를 회원 객체에 설정
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs); // 리소스 해제
        }
    }

    // DataSourceUtils를 사용하여 커넥션 획득
    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
    }

    // 리소스 해제
    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DataSourceUtils를 사용하여 커넥션 릴리스
    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);
    }
}
