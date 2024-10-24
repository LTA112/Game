/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.AccountDAO;
import DAOs.ScoreDAO;
import Models.Account;
import Models.Score;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author lamth
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("btnReg") != null) {
            String username = request.getParameter("txtUserName");
            String pass1 = hash(request.getParameter("txtPass1"));
            String pass2 = request.getParameter("txtPass2");
            String nickname = request.getParameter("txtNickName");

            AccountDAO accDAO = new AccountDAO();
            Account accObj = new Account(username, pass1, nickname);

            ScoreDAO sDAO = new ScoreDAO();
            // Temporary ID set to 0; the actual ID will be obtained after inserting the account
            Score scoreObj = new Score(0, 0);

            boolean addAccountYet = accDAO.addAccount(accObj);
            if (addAccountYet) {
                // Get the ID of the newly registered account
                int accountId = accDAO.getIdFromUsername(username);
                scoreObj.setAccount_id(accountId);

                boolean addScoreYet = sDAO.insertScore(scoreObj);

                if (addScoreYet) {
                    HttpSession session = request.getSession();
                    session.setAttribute("accountId", accountId); // Save accountId in session
                    response.sendRedirect("/FlappyShork/Home");
                } else {
                    response.sendRedirect("/FlappyShork");
                }
            }
        }
        // Get the username parameter from Register.js shit
        // then use it to go to the DB and cooking it
        if (request.getParameter("username") != null) {
            String username = request.getParameter("username");

            AccountDAO accDAO = new AccountDAO();

            boolean exists = accDAO.checkExistUsername(username);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"exists\":" + exists + "}");
        }

        // Get the nickname parameter from Register.js shit
        // then use it to go to the DB and cooking it
        if (request.getParameter("nickname") != null) {
            String nickname = request.getParameter("nickname");

            AccountDAO accDAO = new AccountDAO();

            boolean exists = accDAO.checkExistNickname(nickname);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"exists\":" + exists + "}");
        }
    }

    public String hash(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
