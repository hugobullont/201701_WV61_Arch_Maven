/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Score;

import Entities.Score;
import Hibernate.ArchHibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Usuario
 */
public class ScoreRepository implements IScoreRepository {
    @Override
    public int CalcularPromObjeto(String objectType, int objectId) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();   
        
        int total=0;
        List<Score> result = session.createQuery("from Score as part where part.objectType=:b and part.idObject=:id").setParameter("b",objectType).setParameter("id",objectId).list();
        session.close();
        if(result.size()>0)
        {
            for(int i=0;i<result.size();i++){
                total=total+result.get(i).getScore();
            }
            total=total/result.size();
            return (total);
        }
        else
        {
            return -1;
        }

    }

    @Override
    public void SaveScore(Score objScore) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(objScore);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Score GetScoreByObjeto(String objectType, int objectId, int userId) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession(); 
        List<Score> result = session.createQuery("from Score as part where part.objectType=:b and part.idObject=:id and part.user.idUser=:userId").setParameter("b",objectType).setParameter("id",objectId).setParameter("userId", userId).list();
        session.close();
        if(result.size()>0)
        {
            return result.get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void UpdateScore(Score objScore) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(objScore);
        session.getTransaction().commit();
        session.close();
    }
}
