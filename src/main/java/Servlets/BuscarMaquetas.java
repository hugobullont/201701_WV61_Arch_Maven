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
import Entities.Blueprint;
import Entities.Mockup;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "BuscarMaquetas", urlPatterns = {"/BuscarMaquetas"})
public class BuscarMaquetas extends HttpServlet {

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
        HttpSession session = request.getSession(false);
        
        IMockupsService mkservice = new MockupsService();
        
        String pageTitle = "Buscar Maqueta";
        session.setAttribute("pageTitle", pageTitle);
        
        String listAction= "Search";
        session.setAttribute("listAction",listAction);
        
        String object = "Maqueta";
        session.setAttribute("object",object);
        
        List<Mockup> maquetas = mkservice.GetAllMockups();
        session.setAttribute("listObjects", maquetas);
        
        session.setAttribute("searchString",null);
        RequestDispatcher rdSearchBlueprints = request.getRequestDispatcher("listObjects.jsp");
        rdSearchBlueprints.forward(request, response);
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
        processRequest(request, response);
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