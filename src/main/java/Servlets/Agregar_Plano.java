/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BusinessLogic.Blueprints.*;
import Entities.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;


/**
 *
 * @author Hugo
 */
@WebServlet(name = "Agregar_Plano", urlPatterns = {"/Agregar_Plano"})
@MultipartConfig
public class Agregar_Plano extends HttpServlet {

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
        IBlueprintsService blueprintService = new BlueprintsService();
        User cUser = (User) session.getAttribute("objUser");
        Blueprint objBlueprint = new Blueprint();
        
        String name = request.getParameter("txtnamePlano");
        String description = request.getParameter("txtAreaDescriptionPlano");
        
        
        Part filePart = request.getPart("fileBlueprints");
        String fileName = filePart.getSubmittedFileName();
        String[] fileNameFull = fileName.split("\\.");
        String fileExtension = fileNameFull[1];
        InputStream fileContent = filePart.getInputStream();
        byte[] bytes = IOUtils.toByteArray(fileContent);
        
        objBlueprint.setName(name);
        objBlueprint.setDescription(description);
        objBlueprint.setUser(cUser);
        objBlueprint.setBlueprintFile(bytes);
        objBlueprint.setFileType(fileExtension);
        
        blueprintService.SaveBlueprint(objBlueprint);
        
        RequestDispatcher rdHome = request.getRequestDispatcher("home.jsp");
        rdHome.forward(request, response);
        
        
        
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
