/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccess.Comments;

import Entities.Comment;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface ICommentsRepository {
    public void SaveComment(Comment objComment);
    public void DeleteComment(Comment objComment);
    public List<Comment> GetAllCommentsFromObject(String objectType, int objectId);
}
