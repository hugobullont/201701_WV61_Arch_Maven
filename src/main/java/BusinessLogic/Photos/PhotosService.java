/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BusinessLogic.Photos;

import DataAccess.Photos.*;
import Entities.Photo;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class PhotosService implements IPhotosService{

    @Override
    public List<Photo> GetPhotosByMockupId(int mockupId) {
        IPhotosRepository repo = new PhotosRepository();
        return repo.GetPhotosByMockupId(mockupId);
    }

    @Override
    public Photo GetFirstPhotoByMockupId(int mockupId) {
        IPhotosRepository repo = new PhotosRepository();
        return repo.GetFirstPhotoByMockupId(mockupId);
    }

    @Override
    public void SavePhoto(Photo objPhoto) {
        IPhotosRepository repo = new PhotosRepository();
        repo.SavePhoto(objPhoto);
    }

    @Override
    public Photo GetPhotoById(int photoId) {
        IPhotosRepository repo = new PhotosRepository();
        return repo.GetPhotoById(photoId);
    }
    
}
