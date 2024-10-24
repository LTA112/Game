/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import DB.DBConnection;
import Models.Score;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lamth
 */
public class ScoreDAO {

    public boolean updateScore(Score s) {
        Connection conn = DB.DBConnection.getConnection();
        try {
            // Câu lệnh SQL để cập nhật điểm cao nhất cho account_id = 1 (ví dụ)
            String sql = "UPDATE [FlappyBird].[dbo].[score] SET [score] = ? WHERE [account_id] = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, s.getScore());
            pst.setInt(2, s.getAccount_id()); // Thay đổi account_id tương ứng nếu cần

            // Thực thi câu lệnh SQL
            int rowsUpdated = pst.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            return false;
        }
    }

    // Chinhr ID tuyf theo acc dang signIn
    public boolean insertScore(Score s) {
        try ( Connection conn = DBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement("INSERT INTO score (account_id, score) VALUES (?, ?)")) {
//            ps.setInt(1, s.getAccount_id());
            ps.setInt(1, s.getAccount_id());
            ps.setInt(2, s.getScore());
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Score getHighScore(int accountId) {
        Connection conn = DBConnection.getConnection();
        Score scoreObj = new Score();
        try {
            PreparedStatement pst = conn.prepareStatement("SELECT score FROM score WHERE account_id = ?");
            pst.setInt(1, accountId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                scoreObj = new Score(accountId, rs.getInt("score"));
            } else {
                // If no score found for the accountId, return a default score of 0
                scoreObj = new Score(accountId, 0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreObj;
    }

    public boolean deleteScore(String acc_id) {
        Connection conn = DB.DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM [FlappyBird].[dbo].[score] WHERE account_id = ?");
                pst.setString(1, acc_id);

                int result = pst.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
            }
        }
        return false;
    }
}