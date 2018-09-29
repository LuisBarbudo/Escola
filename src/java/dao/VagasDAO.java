package dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Vagas;


public class VagasDAO{


   protected EntityManager em;

        
   public VagasDAO()  {
        EntityManagerFactory emf;
       try {
           emf = Conexao.getConexao();
           em = emf.createEntityManager();
       } catch (Exception ex) {
           Logger.getLogger(VagasDAO.class.getName()).log(Level.SEVERE, null, ex);
           Logger.getLogger("Não foi possível realizar a conexão com a unidade de persistência. Verifique a conexão");
           
       }
        
    }
   
   public Boolean incluir(Vagas obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
             Logger.getLogger(Vagas.class.getName()).log(Level.SEVERE, null, e);
           Logger.getLogger("Erro ao incluir, veja o código acima");
           
            em.getTransaction().rollback();
           
            retorno = false;
            throw e;
        } finally {
            //em.close();
            
        }
        return retorno;
   }

   public Boolean alterar(Vagas obj) {
        Boolean retorno;
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
             Logger.getLogger(VagasDAO.class.getName()).log(Level.SEVERE, null, e);
          Logger.getLogger("Erro ao alterar, veja o código acima"); 
            em.getTransaction().rollback();
            retorno = false;
            throw e;
        } finally {
            // em.close();
        }
        return retorno;
   }

   public Boolean excluir(Vagas obj) {
       Boolean retorno;
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            retorno = true;
        } catch (RuntimeException e) {
             Logger.getLogger(VagasDAO.class.getName()).log(Level.SEVERE, null, e);
             Logger.getLogger("Erro ao excluir, veja o código acima"); 
          
            em.getTransaction().rollback();
            retorno = false;
        } finally {
            //em.close();
        }
        return retorno;
   }

   public List<Vagas> listar() {
      return em.createNamedQuery("Vagas.findAll").getResultList();
   }
   
   public List<Vagas> listar(String filtro){
        return em.createNamedQuery("Vagas.findFilter").setParameter("filtro","%" + filtro.toUpperCase() + "%").getResultList();
    }

   public Vagas buscarPorChavePrimaria(Integer chaveprimaria) {
       return em.find(Vagas.class, chaveprimaria);
   }
   
   public void fecharConexao() {
        Conexao.closeConexao();
    }
   
   
   
   
   
}
