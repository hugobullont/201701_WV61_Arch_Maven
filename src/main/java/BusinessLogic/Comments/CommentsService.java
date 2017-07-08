/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Comments;

import DataAccess.Comments.*;
import Entities.Comment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class CommentsService implements ICommentsService{

    @Override
    public void SaveComment(Comment objComment) {
        ICommentsRepository repo = new CommentsRepository();
        repo.SaveComment(objComment);
    }

    @Override
    public void DeleteComment(Comment objComment) {
        ICommentsRepository repo = new CommentsRepository();
        repo.DeleteComment(objComment);
    }

    @Override
    public List<Comment> GetAllCommentsFromObject(String objectType, int objectId) {
        ICommentsRepository repo = new CommentsRepository();
        return repo.GetAllCommentsFromObject(objectType, objectId);
    }
    
}
