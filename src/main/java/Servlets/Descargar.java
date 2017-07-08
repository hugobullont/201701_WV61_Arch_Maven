/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BusinessLogic.Blueprints.*;
import BusinessLogic.Mockups.IMockupsService;
import BusinessLogic.Mockups.MockupsService;
import Entities.Blueprint;
import Entities.Mockup;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static org.hibernate.internal.util.io.StreamCopier.BUFFER_SIZE;

/**
 *
 * @author Usuario
 */
@WebServlet(name = "Descargar", urlPatterns = {"/Descargar"})
public class Descargar extends HttpServlet {

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
            out.println("<title>Servlet Descargar</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Descargar at " + request.getContextPath() + "</h1>");
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
        
        IBlueprintsService bpservice = new BlueprintsService();
        int objectId = Integer.parseInt(request.getParameter("objectId"));
        String objectType = request.getParameter("objectType");
        int fileLength = 0;
        String fileName = null;
        Blueprint objectBP = new Blueprint();
        byte[] bytes;
        InputStream inputStream = null;
        
        if(objectType.contains("P"))
        {
            objectBP = bpservice.GetBlueprintById(objectId);
            fileName = objectBP.getName() +  "." + objectBP.getFileType().toLowerCase();
            bytes = objectBP.getBlueprintFile();
            inputStream = new ByteArrayInputStream(bytes); 
            fileLength = inputStream.available();
            session.setAttribute("informationObjectType", objectType);
            session.setAttribute("informationObject", objectBP);
        }
        
        System.out.println("fileLength = " + fileLength);
        ServletContext context = getServletContext();
 
                // sets MIME type for the file download
                String mimeType = context.getMimeType(fileName);
                if (mimeType == null) {        
                    mimeType = "application/octet-stream";
                }              
                 
                // set content properties and header attributes for the response
                response.setContentType(mimeType);
                response.setContentLength(fileLength);
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", fileName);
                response.setHeader(headerKey, headerValue);
                // writes the file to the client
                OutputStream outStream = response.getOutputStream();
                 
                byte[] buffer = new byte[BUFFER_SIZE];
                int bytesRead = -1;
                 
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
                 
        inputStream.close();
        outStream.close();  
                
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
