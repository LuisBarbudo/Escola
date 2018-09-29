/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CalendarioDAO;
import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Calendario;
import modelo.Usuario;

/**
 *
 * @author marcelosiedler
 */
@WebServlet(name = "siteWS", urlPatterns = {"/sitelista/siteWS"})
public class siteWS extends HttpServlet {

    

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
        String pagina;
        String acao;
        pagina = "index.jsp";
        acao = request.getParameter("acao");
        if(request.getParameter("acao")==null)
        {
            acao = "";
        }
        switch(acao)
        {
            case "Usuario":
                
               
           
                request.setAttribute("usuario", this.listarUsuario());
                pagina = "indexUsuario.jsp";
                break;
            case "Calendario":
                
               
           
                request.setAttribute("calendario", this.listarCalendario());
                pagina = "indexCalendario.jsp";
                break;
                
            
              
            
        }
         RequestDispatcher destino;
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }
    
    public List<Usuario> listarUsuario()
    {
        List<Usuario> lista;
        UsuarioDAO dao = new UsuarioDAO();
        lista = dao.listar();
        dao.fecharConexao();
        return lista;
    }
    public List<Calendario> listarCalendario()
    {
        List<Calendario> lista;
        CalendarioDAO dao = new CalendarioDAO();
        lista = dao.listar();
        dao.fecharConexao();
        return lista;
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
