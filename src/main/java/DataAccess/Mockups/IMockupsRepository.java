/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Mockups;

import Entities.Mockup;
import java.util.List;

/**
 *
 * @author Hugo
 */
public interface IMockupsRepository {
    public void SaveMockup(Mockup objMockup);
    public void UpdateMockup(Mockup objMockup);
    public List<Mockup> GetAllMockups();
    public List<Mockup> GetMockupsByString(String search);
    public List<Mockup> GetMockupsByUserId(int userId);
    public Mockup GetMockupById(int mockupId);
}
