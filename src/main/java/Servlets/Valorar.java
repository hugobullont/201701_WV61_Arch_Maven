/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BusinessLogic.Blueprints.BlueprintsService;
import BusinessLogic.Blueprints.IBlueprintsService;
import BusinessLogic.Mockups.IMockupsService;
import BusinessLogic.Mockups.MockupsService;
import BusinessLogic.Score.*;
import Entities.Blueprint;
import Entities.Mockup;
import Entities.Score;
import Entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Valorar", urlPatterns = {"/Valorar"})
public class Valorar extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Valorar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Valorar at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(false);
        
        IBlueprintsService bpService = new BlueprintsService();
        IMockupsService mkService = new MockupsService();
        IScoreService scoreService = new ScoreService();
        
        User objUser = (User) session.getAttribute("objUser");
        String value = request.getParameter("valueGroup");
        int intValue = Integer.valueOf(value);
        
        int objectId = Integer.parseInt(request.getParameter("objectId"));
        String objectType = request.getParameter("objectType");
        
        Score objScore = scoreService.GetScoreByObjeto(objectType, objectId, objUser.getIdUser());
        
        if(objScore == null)
        {
            objScore = new Score();
            objScore.setIdObject(objectId);
            objScore.setObjectType(objectType);
            objScore.setUser(objUser);
            objScore.setScore(intValue);
            scoreService.SaveScore(objScore);
        }
        else
        {
            objScore.setScore(intValue);
            scoreService.UpdateScore(objScore);
        }
        
        if("P".equals(objectType))
        {
            Blueprint objBlueprint = bpService.GetBlueprintById(objectId);
            session.setAttribute("informationObjectType", objectType);
            session.setAttribute("informationObject", objBlueprint);
        }
        
        if("M".equals(objectType))
        {
            Mockup objMockup = mkService.GetMockupById(objectId);
            session.setAttribute("informationObjectType", objectType);
            session.setAttribute("informationObject", objMockup);
        }
        RequestDispatcher rdInfo = request.getRequestDispatcher("objectInformation.jsp");
        rdInfo.forward(request, response);
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
