/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOs;

import Models.Account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lamth
 */
public class AccountDAO {

    public boolean checkMatchPassword(String pass1, String pass2) {
        if (pass1.equals(pass2)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean checkExistUsername(String username) {
        Connection conn = DB.DBConnection.getConnection();

        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM [FlappyBird].[dbo].[account] where username = ?");

            pst.setString(1, username);

            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean checkExistNickname(String nickname) {
        Connection conn = DB.DBConnection.getConnection();

        try {
            PreparedStatement pst = conn.prepareStatement("SELECT * FROM [FlappyBird].[dbo].[account] where nickname = ?");

            pst.setString(1, nickname);

            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
        }
        return false;
    }

    public boolean addAccount(Account acc) {
        Connection conn = DB.DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("INSERT INTO account( username, password, nickname) VALUES (?, ?, ?)");

                pst.setString(1, acc.getUsername());
                pst.setString(2, acc.getPassword());
                pst.setString(3, acc.getNickname());
                int result = pst.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public boolean deleteAccount(String acc_id) {
        Connection conn = DB.DBConnection.getConnection();
        if (conn != null) {
            try {
                PreparedStatement pst = conn.prepareStatement("DELETE FROM [FlappyBird].[dbo].[account] WHERE id = ?");
                pst.setString(1, acc_id);

                int result = pst.executeUpdate();
                return result > 0;
            } catch (SQLException e) {
            }
        }
        return false;
    }

    public Account checkLogin(String username, String password) {
        try ( Connection conn = DB.DBConnection.getConnection();  PreparedStatement pst = conn.prepareStatement("SELECT * FROM [FlappyBird].[dbo].[account] WHERE username = ? AND password = ?")) {

            pst.setString(1, username);
            pst.setString(2, password);

            try ( ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Account acc = new Account();
                    acc.setId(rs.getInt("id"));
                    acc.setUsername(rs.getString("username"));
                    acc.setPassword(rs.getString("password"));
                    acc.setNickname(rs.getString("nickname"));
                    return acc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getIdFromUsername(String username) {
        Connection conn = DB.DBConnection.getConnection();
        int result = -1; // giá trị mặc định nếu không tìm thấy username

        if (conn != null) {
            try {
                String sql = "SELECT id FROM [FlappyBird].[dbo].[account] WHERE [username] = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, username);

                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    result = rs.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace(); // In ra lỗi để dễ dàng debug
            } finally {
                try {
                    conn.close(); // Đảm bảo đóng kết nối sau khi thực hiện xong
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}