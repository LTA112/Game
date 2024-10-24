/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAOs.ScoreDAO;
import Models.Score;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author lamth
 */
public class ScoreController extends HttpServlet {

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
            out.println("<title>Servlet ScoreController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ScoreController at LMAO" + request.getContextPath() + "</h1>");
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
        // Get the account ID from the request
        String accountIdChk = request.getParameter("accountId");
        if (accountIdChk != null) {
            try {
                int accountId = Integer.parseInt(accountIdChk);
                ScoreDAO sDAO = new ScoreDAO();
                Score score = sDAO.getHighScore(accountId);

                response.setContentType("application/json");
                PrintWriter out = response.getWriter();
                out.print("{\"score\":" + score.getScore() + "}");
                out.flush();
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid account ID");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Account ID is missing");
        }
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
        // Take the request from game.js

        String highScoreStr = request.getParameter("highScore");
        String accountIdStr = request.getParameter("accountId");

        if (highScoreStr != null && accountIdStr != null) {
            try {
                int highScore = Integer.parseInt(highScoreStr);
                int accountId = Integer.parseInt(accountIdStr);

                ScoreDAO scoreDAO = new ScoreDAO();
                Score score = new Score(accountId, highScore);
                boolean success = scoreDAO.updateScore(score);
                if (!success) {
                    success = scoreDAO.insertScore(score);
                }

                response.getWriter().write(success ? "YES YES YES" : "NO NO NO NO");
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID DEO ON");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "MAT ME ID");
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
