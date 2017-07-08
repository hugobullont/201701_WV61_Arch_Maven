/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Mockups;

import DataAccess.Mockups.*;
import Entities.Mockup;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class MockupsService implements IMockupsService{

    @Override
    public void SaveMockup(Mockup objMockup) {
        IMockupsRepository repo = new MockupsRepository();
        repo.SaveMockup(objMockup);
    }

    @Override
    public void UpdateMockup(Mockup objMockup) {
        IMockupsRepository repo = new MockupsRepository();
        repo.UpdateMockup(objMockup);
    }

    @Override
    public List<Mockup> GetAllMockups() {
        IMockupsRepository repo = new MockupsRepository();
        return repo.GetAllMockups();
    }

    @Override
    public List<Mockup> GetMockupsByString(String search) {
        IMockupsRepository repo = new MockupsRepository();
        return repo.GetMockupsByString(search);
    }

    @Override
    public List<Mockup> GetMockupsByUserId(int userId) {
        IMockupsRepository repo = new MockupsRepository();
        return repo.GetMockupsByUserId(userId);
    }

    @Override
    public Mockup GetMockupById(int mockupId) {
        IMockupsRepository repo = new MockupsRepository();
        return repo.GetMockupById(mockupId);
    }
    
}
