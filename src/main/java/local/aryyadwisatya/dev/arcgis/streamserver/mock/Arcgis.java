/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package local.aryyadwisatya.dev.arcgis.streamserver.mock;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adwisatya
 */
@WebServlet(name = "Arcgis", urlPatterns = {"/arcgis/*"})
public class Arcgis extends HttpServlet {

    @Override
    public void init(){
        ServletContext context = getServletContext();
        try {
            context.setAttribute("restInfoData", new String(Files.readAllBytes(Paths.get(this.getServletContext().getRealPath("/ArcgisRestInfo.json")))));
            context.setAttribute("streamServerInfoData", new String(Files.readAllBytes(Paths.get(this.getServletContext().getRealPath("/StreamServerInfo.json")))));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Arcgis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Arcgis.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param message
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(String contentType,String message,HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ServletContext context = getServletContext();
        response.setContentType(contentType);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.print(message);
        }
    }

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
        ServletContext context = getServletContext();
        StringBuffer message = new StringBuffer();
        String contentType = null;
        
        if(request.getRequestURI().contains("/arcgis/rest/info")){
            processRequest("application/json",context.getAttribute("restInfoData").toString(),request, response);
        }
        if(request.getRequestURI().matches(".*/arcgis/rest/services/.*/StreamServer")){
            if(request.getParameterMap().containsKey("callback")){
                message.append(request.getParameter("callback"));
                message.append("(");
                message.append(context.getAttribute("streamServerInfoData"));
                message.append(");");
                
                contentType = "text/html";
            }else{
                message.append(context.getAttribute("streamServerInfoData"));
                contentType = "application/json";
            }
            processRequest(contentType,message.toString(),request, response);
        }
        
    }

}
