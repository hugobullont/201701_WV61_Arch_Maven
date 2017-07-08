/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import BusinessLogic.Mockups.IMockupsService;
import BusinessLogic.Mockups.MockupsService;
import BusinessLogic.Photos.IPhotosService;
import BusinessLogic.Photos.PhotosService;
import Entities.Mockup;
import Entities.Photo;
import Entities.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
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
@WebServlet(name = "Agregar_Maqueta", urlPatterns = {"/Agregar_Maqueta"})
@MultipartConfig
public class Agregar_Maqueta extends HttpServlet {

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
            out.println("<title>Servlet Agregar_Maqueta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Agregar_Maqueta at " + request.getContextPath() + "</h1>");
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
        IMockupsService mkService = new MockupsService();
        IPhotosService photoService = new PhotosService();
        User cUser = (User) session.getAttribute("objUser");
        Mockup objMockup = new Mockup();
        
        String name = request.getParameter("txtnameMaqueta");
        String description = request.getParameter("txtAreaDescriptionMaqueta");
        
        
        
        objMockup.setUser(cUser);
        objMockup.setDescription(description);
        objMockup.setName(name);
        
        mkService.SaveMockup(objMockup);
        
        List<Part> fileParts = request.getParts().stream().filter(part -> "filePhotos".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">

        for (Part filePart : fileParts) {
            Photo objPhoto = new Photo();
            String fileName = filePart.getSubmittedFileName();
            String[] fileNameFull = fileName.split("\\.");
            String fileExtension = fileNameFull[1];
            InputStream fileContent = filePart.getInputStream();
            byte[] bytes = IOUtils.toByteArray(fileContent);
            
            objPhoto.setFile(bytes);
            objPhoto.setFileType(fileExtension);
            objPhoto.setMockup(objMockup);
            photoService.SavePhoto(objPhoto);
        }
        
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
