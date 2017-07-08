/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Blueprints;

import DataAccess.Blueprints.*;
import Entities.Blueprint;
import java.util.List;

/**
 *
 * @author Hugo
 */
public class BlueprintsService implements IBlueprintsService{

    @Override
    public void SaveBlueprint(Blueprint objBlueprint) {
        IBlueprintsRepository repo = new BlueprintsRepository();
        repo.SaveBlueprint(objBlueprint);
    }

    @Override
    public void UpdateBlueprint(Blueprint objBlueprint) {
        IBlueprintsRepository repo = new BlueprintsRepository();
        repo.UpdateBlueprint(objBlueprint);
    }

    @Override
    public List<Blueprint> GetAllBlueprints() {
        IBlueprintsRepository repo = new BlueprintsRepository();
        return repo.GetAllBlueprints();
    }

    @Override
    public List<Blueprint> GetBlueprintsByString(String search) {
        IBlueprintsRepository repo = new BlueprintsRepository();
        return repo.GetBlueprintsByString(search);
    }

    @Override
    public List<Blueprint> GetBlueprintsByUserId(int userId) {
        IBlueprintsRepository repo = new BlueprintsRepository();
        return repo.GetBlueprintsByUserId(userId);
    }

    @Override
    public Blueprint GetBlueprintById(int blueprintId) {
        IBlueprintsRepository repo = new BlueprintsRepository();
        return repo.GetBlueprintById(blueprintId);
    }
    
}
