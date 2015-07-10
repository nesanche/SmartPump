package com.smartpump.resources;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.smartpump.dao.interfaces.IUserDao;
import com.smartpump.utils.FileUploader;
import com.smartpump.utils.RestBoolean;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * Representa el punto de entrada para la API Rest en el manejo de usuarios.
 * 
 * @author Franco Ariel Salonia
 *
 */
@Component
@Path("/users")
public class UserResource extends AbstractResource {

    /** El Controlador DAO relacionado al recurso. */
    @Autowired
    private IUserDao userDao;
    /** Responsable la subida de archivos. */
    @Autowired
    private FileUploader fileUploader;
    /** Ruta donde se almacenan las imágenes. */
    private static final String PICTURES_PATH = "/home/ec2-user/Bismara/pictures/";

    /**
     * Establece el objeto para el manejo de JSON. Usado por Spring
     * 
     * @param gson
     *            el objeto para el manejo de JSON.
     */
    public void setGson(Gson gson) {
        this.gson = gson;
    }

    /**
     * Establece cual va a ser el DAO a utilizar.
     * 
     * @param userDao
     *            el controlador DAO relacionado al recurso.
     */
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * Verifica que si un nombre de usuario puede ser utilizado para la creación
     * de uno nuevo.
     * 
     * @param username
     *            el nombre de usuario a verificar.
     * @return true si puede ser utilizado, false en caso contrario.
     */
    @Path("/verifyUsername")
    @GET
    @Produces({ MediaType.TEXT_PLAIN })
    public Response verifyUsername(@QueryParam("username") String username) {
        RestBoolean response = new RestBoolean(!userDao.validateUser(username));
        return responseBuilder.buildResponse(200, response.toString());
    }

    @Path("/uploadPicture")
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadPicture(
            @FormDataParam("file") InputStream uploadedInputStream,
            @FormDataParam("file") FormDataContentDisposition fileDetail,
            @HeaderParam("user-id") int userId) {
        String uploadedFileLocation = PICTURES_PATH + userId;

        fileUploader.writeToFile(uploadedInputStream, uploadedFileLocation);

        String output = "File uploaded to : " + uploadedFileLocation;

        return responseBuilder.buildResponse(200, output);
    }
}
