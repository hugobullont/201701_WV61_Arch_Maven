/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Comments;

import Entities.Comment;
import Hibernate.ArchHibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class CommentsRepository implements ICommentsRepository{

    @Override
    public void SaveComment(Comment objComment) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(objComment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void DeleteComment(Comment objComment) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(objComment);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Comment> GetAllCommentsFromObject(String objectType, int objectId) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        List<Comment> result = session.createQuery("from Comment as part where part.objectType=:b and part.idObject=:id").setParameter("b",objectType).setParameter("id",objectId).list();
        session.close();
        
        return result;
    }
    
}
