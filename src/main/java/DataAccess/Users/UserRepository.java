/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Users;

import Entities.User;
import Hibernate.ArchHibernateUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @author Usuario
 */
public class UserRepository implements IUserRepository {

    @Override
    public String GetNameByUserId(String userid, String accessToken) {
        String name;
        String sUrl = "https://graph.facebook.com/"+ userid +"?access_token="+accessToken;
        
        try
        {
            URL url = new URL(sUrl);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootobj = root.getAsJsonObject();
            name = rootobj.get("name").getAsString();
        }
        catch(JsonIOException | JsonSyntaxException | IOException ex)
        {
            return "undefined";        
        }
        
        return name;
    }

    @Override
    public boolean FBUserIsRegistered(String userid) {
       if(this.GetUserByFBId(userid) != null)
       {
           return true;
       }
       else
       {
           return false;
       }
    }

    @Override
    public User GetUserByFBId(String userid) {
       Session session = ArchHibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       Criteria criteria = session.createCriteria(User.class);
       User user = (User) criteria.add(Restrictions.eq("fbId",userid)).uniqueResult();
       session.close();
       return user;
    }

    @Override
    public void SaveUser(User objUser) {
        Session session = ArchHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(objUser);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String GetProfilePictureUrlByFBId(String userid) {
        String url = "https://graph.facebook.com/" + userid + "/picture?type=large";
        return url;
    }

    @Override
    public String GetFirstNameByUserId(String userid) {
        User user = this.GetUserByFBId(userid);
        return user.getName();
    }
    
}
