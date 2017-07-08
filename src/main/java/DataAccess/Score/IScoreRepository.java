/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Score;

import Entities.Score;

/**
 *
 * @author Usuario
 */
public interface IScoreRepository {
    public int CalcularPromObjeto(String objectType, int objectId);
    public void SaveScore(Score objScore);
    public Score GetScoreByObjeto(String objectType, int objectId, int userId);
    public void UpdateScore(Score objScore);
}
