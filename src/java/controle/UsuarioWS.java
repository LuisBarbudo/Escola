/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Usuario;

/**
 *
 * @author Luisf
 */
@WebServlet(name = "UsuarioWS", urlPatterns = {"/usuario/UsuarioWS"})
public class UsuarioWS extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
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
            throws ServletException, IOException { //Pega qual a ação desejo executar
        String acao = request.getParameter("acao");
        String pagina;
        if(request.getParameter("acao")==null)
        {
            acao = "";
        }
        switch(acao){
            case "del":
                if(request.getParameter("id")!=null){
                Integer id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("msg", this.excluir(id));
                pagina = "del.jsp";
                }
                else{
                    request.setAttribute("msg", "Identificador não informado");
                }
                pagina = "del.jsp";
                break;
                
                
            case "upd":
                if(request.getParameter("id")!=null){
                Integer id = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("obj", this.buscarporPK(id));
                pagina = "upd.jsp";
                }
                else{
                    request.setAttribute("msg", "Identificador não informado");
                    pagina = "upd-ok.jsp";
                }
                break;
                
            case "filter":
                pagina = "index.jsp";
                //Pega a informação digitada pelo usuário do sistema
                String filtro = request.getParameter("txtFiltro");
                List<Usuario> lista2 = this.listar(filtro);
                request.setAttribute("lista", lista2);
                break;
                
            default: 
                List<Usuario> lista = this.listar();
                request.setAttribute("lista", lista);
                pagina = "index.jsp";
                break;
        }
        RequestDispatcher destino;
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }
    
    public Usuario buscarporPK(Integer id){
        Usuario retorno;
        UsuarioDAO dao = new UsuarioDAO();
        retorno = dao.buscarPorChavePrimaria(id);
        dao.fecharConexao();
        return retorno;
    }
    
    public String excluir(Integer id){
        String retorno;
        UsuarioDAO dao = new UsuarioDAO();
        Usuario obj = dao.buscarPorChavePrimaria(id);
        if(dao.excluir(obj)){
            retorno = "Exclusão realizada com SUCESSO!!!";
        }
        else{
            retorno = "Não foi possível excluir verifique dependências.";
        }
        dao.fecharConexao();
        return retorno;
    }
    
    public List<Usuario> listar(){
        List<Usuario> lista;
        UsuarioDAO dao = new UsuarioDAO();
        lista = dao.listar();
        dao.fecharConexao();
        return lista;
        
    }
    
    public List<Usuario> listar(String filtro){
        List<Usuario> lista;
        UsuarioDAO dao = new UsuarioDAO();
        try{
        lista = dao.listar(filtro);
        }catch(Exception e)
        {
            Logger.getLogger("ERRO AO FILTRAR");
            lista = new ArrayList<>();
        }
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
       
        String pagina;
        String msg;
        pagina = "add-ok.jsp";
        
        //Verifivo se os campos obrigatórios foram informados e se eu estou programando direito o formulário
        if(request.getParameter("txtNome")!=null&&request.getParameter("txtSenha")!=null&&request.getParameter("txtEmail")!=null){
            String nome = request.getParameter("txtNome");
            String senha = request.getParameter("txtSenha");
            String email = request.getParameter("txtEmail");
            Usuario obj = new Usuario();
            obj.setNome(nome);
            obj.setEmail(email);
            obj.setSenha(senha);
            UsuarioDAO dao = new UsuarioDAO();
            //Verifica se é alteração ou cadastro
            if(request.getParameter("txtId")==null){
            if(dao.incluir(obj)){
                msg = "Registro cadastrado com SUCESSO!!!";
                
            }
            else{
                msg = "Erro ao cadastrar.";
                //Erro na gravação no banco de dados, ver aba APACHE
            }
         }
            else{
                //Passar a chave primária e mandar alterar
                String id = request.getParameter("txtId");
                obj.setId(Integer.parseInt(id));
                if(dao.alterar(obj)){
                    msg = "Registro alterado com SUCESSO!!!";
                }
                else{
                    msg = "Erro ao alterar";
                } 
                pagina = "upd-ok.jsp";
            }  
            
            dao.fecharConexao();
            
        }
        else{
            msg = "Campos obrigatórios não informados.";
        }
        request.setAttribute("msg", msg);
        RequestDispatcher destino;
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
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

