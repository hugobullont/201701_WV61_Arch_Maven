/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Score;

import DataAccess.Score.*;
import Entities.Score;
import Entities.User;

/**
 *
 * @author Usuario
 */
public class ScoreService implements IScoreService{

    @Override
    public int GetPromObject(String objectType, int objectId) {
        IScoreRepository scoreRepo = new ScoreRepository();
        return scoreRepo.CalcularPromObjeto(objectType, objectId);
    }

    @Override
    public void SaveScore(User objUser, int score, String objType, int objId) {
        IScoreRepository scoreRepo = new ScoreRepository();
        Score objScore = new Score();
        objScore.setIdObject(objId);
        objScore.setScore(score);
        objScore.setObjectType(objType);
        objScore.setUser(objUser);
        scoreRepo.SaveScore(objScore);
    }

    @Override
    public void SaveScore(Score objScore) {
        IScoreRepository scoreRepo = new ScoreRepository();
        scoreRepo.SaveScore(objScore);
    }

    @Override
    public Score GetScoreByObjeto(String objectType, int objectId, int userId) {
        IScoreRepository scoreRepo = new ScoreRepository();
        return scoreRepo.GetScoreByObjeto(objectType, objectId, userId);
    }

    @Override
    public void UpdateScore(Score objScore) {
        IScoreRepository scoreRepo = new ScoreRepository();
        scoreRepo.UpdateScore(objScore);
    }
               
}
