/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Users;

import Entities.User;

/**
 *
 * @author Usuario
 */
public interface IUserRepository {
    public String GetNameByUserId(String userid, String accessToken);
    public String GetFirstNameByUserId(String userid);
    public boolean FBUserIsRegistered(String userid);
    public String GetProfilePictureUrlByFBId(String userid);
    public User GetUserByFBId(String userid);
    public void SaveUser(User objUser);
}
