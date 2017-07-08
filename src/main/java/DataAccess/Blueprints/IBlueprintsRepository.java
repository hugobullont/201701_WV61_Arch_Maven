/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Blueprints;

import Entities.Blueprint;
import java.util.List;

/**
 *
 * @author Hugo
 */
public interface IBlueprintsRepository {
    public void SaveBlueprint(Blueprint objBlueprint);
    public void UpdateBlueprint(Blueprint objBlueprint);
    public List<Blueprint> GetAllBlueprints();
    public List<Blueprint> GetBlueprintsByString(String search);
    public List<Blueprint> GetBlueprintsByUserId(int userId);
    public Blueprint GetBlueprintById(int blueprintId);
}
