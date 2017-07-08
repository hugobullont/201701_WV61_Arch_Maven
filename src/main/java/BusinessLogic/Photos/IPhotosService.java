/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Photos;

import Entities.Photo;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IPhotosService {
    public List<Photo> GetPhotosByMockupId(int mockupId);
    public Photo GetFirstPhotoByMockupId(int mockupId);
    public void SavePhoto(Photo objPhoto);
    public Photo GetPhotoById(int photoId);
}
