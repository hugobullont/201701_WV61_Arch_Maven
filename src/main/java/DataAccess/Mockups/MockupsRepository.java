/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Mockups;

import Entities.Blueprint;
import Entities.Mockup;
import Hibernate.ArchHibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author Hugo
 */
public class MockupsRepository implements IMockupsRepository{

    @Override
    public void SaveMockup(Mockup objMockup) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(objMockup);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void UpdateMockup(Mockup objMockup) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(objMockup);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Mockup> GetAllMockups() {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Mockup> mockups = (List<Mockup>)session.createQuery("FROM Mockup").list();
        session.close();
        return mockups;
    }

    @Override
    public List<Mockup> GetMockupsByString(String search) {
        List<Mockup> mockups = new ArrayList<Mockup>();
        String searchLower = search.toLowerCase();
        for(Mockup mk: this.GetAllMockups())
        {
            String name = mk.getName().toLowerCase();
            if(name.contains(searchLower))
            {
                mockups.add(mk);
            }
        }

        return mockups;
    }

    @Override
    public List<Mockup> GetMockupsByUserId(int userId) {
        List<Mockup> mockups = new ArrayList<Mockup>();
        for(Mockup mk: this.GetAllMockups())
        {
            if(mk.getUser().getIdUser() == userId)
            {
                mockups.add(mk);
            }
        }

        return mockups;
    }

    @Override
    public Mockup GetMockupById(int mockupId) {
        for(Mockup mk: this.GetAllMockups())
        {
            if(mk.getIdMockup()== mockupId)
            {
                return mk;
            }
        }
        
        return null;
    }
    
}
