/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import dao.CalendarioDAO;
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
import modelo.Calendario;

/**
 *
 * @author Luisf
 */
@WebServlet(name = "CalendarioWS", urlPatterns = {"/calendario/CalendarioWS"})
public class CalendarioWS extends HttpServlet {

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
                List<Calendario> lista2 = this.listar(filtro);
                request.setAttribute("lista", lista2);
                break;
                
            default: 
                List<Calendario> lista = this.listar();
                request.setAttribute("lista", lista);
                pagina = "index.jsp";
                break;
        }
        RequestDispatcher destino;
        destino = request.getRequestDispatcher(pagina);
        destino.forward(request, response);
        
    }
    
    public Calendario buscarporPK(Integer id){
        Calendario retorno;
        CalendarioDAO dao = new CalendarioDAO();
        retorno = dao.buscarPorChavePrimaria(id);
        dao.fecharConexao();
        return retorno;
    }
    
    public String excluir(Integer id){
        String retorno;
        CalendarioDAO dao = new CalendarioDAO();
        Calendario obj = dao.buscarPorChavePrimaria(id);
        if(dao.excluir(obj)){
            retorno = "Exclusão realizada com SUCESSO!!!";
        }
        else{
            retorno = "Não foi possível excluir verifique dependências.";
        }
        dao.fecharConexao();
        return retorno;
    }
    
    public List<Calendario> listar(){
        List<Calendario> lista;
        CalendarioDAO dao = new CalendarioDAO();
        lista = dao.listar();
        dao.fecharConexao();
        return lista;
        
    }
    
    public List<Calendario> listar(String filtro){
        List<Calendario> lista;
        CalendarioDAO dao = new CalendarioDAO();
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
        if(request.getParameter("txtDia")!=null&&request.getParameter("txtMes")!=null&&request.getParameter("txtAno")!=null&&request.getParameter("txtDescricao")!=null){
            Integer dia = Integer.parseInt(request.getParameter("txtDia"));
            Integer mes = Integer.parseInt(request.getParameter("txtMes"));
            Integer ano = Integer.parseInt(request.getParameter("txtAno"));
            String descricao  = request.getParameter("txtDescricao");
            Calendario obj = new Calendario();
            obj.setDia(dia);
            obj.setMes(mes);
            obj.setAno(ano);
            obj.setDescricao(descricao);
            CalendarioDAO dao = new CalendarioDAO();
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
