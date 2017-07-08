<%-- 
    Document   : objectInformation
    Created on : 23-jun-2017, 23:25:02
    Author     : Hugo
--%>

<%@page import="Entities.Comment"%>
<%@page import="BusinessLogic.Comments.CommentsService"%>
<%@page import="BusinessLogic.Comments.ICommentsService"%>
<%@page import="BusinessLogic.Photos.PhotosService"%>
<%@page import="BusinessLogic.Photos.IPhotosService"%>
<%@page import="Entities.Photo"%>
<%@page import="java.util.List"%>
<%@page import="Entities.User"%>
<%@page import="BusinessLogic.Score.*"%>
<%@page import="Entities.Mockup"%>
<%@page import="Entities.Blueprint"%>
<%@page import="BusinessLogic.Users.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession httpsession = request.getSession(false);
    IUserService userService = new UserService();
    IScoreService scoreService = new ScoreService();
    IPhotosService photoService = new PhotosService();
    ICommentsService commentService = new CommentsService();
    
    String uid = (String) httpsession.getAttribute("uid");
    String accessToken = (String) httpsession.getAttribute("accessToken");
    String name = (String) httpsession.getAttribute("name");
    String imgurl = userService.GetProfilePictureUrlByFBId(uid);
    String objType = (String) session.getAttribute("informationObjectType");
    Blueprint objectBlueprint = new Blueprint();
    Mockup objectMockup = new Mockup();
    
    User objUser = (User) session.getAttribute("objUser");
    
    int objectId = 0;
    
    
    boolean owner = false;
    
    if(objType.contains("P"))
    {
        objectBlueprint = (Blueprint)session.getAttribute("informationObject");
        if(objectBlueprint.getUser().getIdUser() == objUser.getIdUser()) owner = true;
        objectId = objectBlueprint.getIdBlueprint();
    }
    if(objType.contains("M"))
    {
         objectMockup = (Mockup)session.getAttribute("informationObject");
         if(objectMockup.getUser().getIdUser() == objUser.getIdUser()) owner = true;
         objectId = objectMockup.getIdMockup();
    }
    
    List<Comment> comments = commentService.GetAllCommentsFromObject(objType, objectId);
    
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>arch - Informacion</title>
        <!-- CSS  -->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
        <link href="css/padding.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    </head>
    <body background="resources/trama.jpg">
        <ul id="slide-out" class="side-nav fixed">
            <li>
                <div class="userView">
                    <div class="background">
                        <img width="500px" src="resources/background2.jpg">
                    </div>
                    <img class="circle" src="<%out.println(imgurl);%>">
                    <span class="white-text name"><%out.println(name);%></span>
                    <span class="white-text email"></span>
                </div>
            </li>
            <li><a class="subheader">Planos</a></li>
            <li><a class="waves-effect" href="BuscarPlanos"><i class="material-icons">find_in_page</i>Buscar Plano</a></li>
            <li><a class="waves-effect" href="AgregarPlano"><i class="material-icons">add_circle</i>Agregar Plano</a></li>
            <li><a class="waves-effect" href="MisPlanos"><i class="material-icons">dns</i>Mis Planos</a></li>
            <li><a class="subheader">Maquetas</a></li>
            <li><a class="waves-effect" href="BuscarMaquetas"><i class="material-icons">find_in_page</i>Buscar Maqueta</a></li>
            <li><a class="waves-effect" href="AgregarMaqueta"><i class="material-icons">add_to_photos</i>Agregar Maqueta</a></li>
            <li><a class="waves-effect" href="MisMaquetas"><i class="material-icons">photo_library</i>Mis Maquetas</a></li>
        </ul>
        <nav class="cyan darken-1 hide-on-large-only" role="navigation" >        
            <a href="#" data-activates="slide-out" class="button-collapse"><i class="material-icons">menu</i></a>
        </nav>
        <main class="row">
            <div class="col s12 m12">
                    <div class="card white">
                        <div class="card-content black-text row">
                            <%if(objType.contains("P")){%>
                            <span class="card-title col s12 m12"><%=objectBlueprint.getName()%></span>
                            <div class="col s6 m6 l6">
                                <h6 class="grey-text text-lighten-1">Descripción</h6>
                                <h6 class="grey-text"><%=objectBlueprint.getDescription()%></h6>
                                <h6 class="grey-text text-lighten-1">Tipo de Archivo</h6>
                                <h6 class="grey-text"><%=objectBlueprint.getFileType().toLowerCase()%></h6>
                                <h6 class="grey-text text-lighten-1">Valoración</h6>
                                <%int score = scoreService.GetPromObject("P", objectBlueprint.getIdBlueprint()); %>
                                <h6 class="grey-text"><%if(score>-1){ out.print(score);} else{out.print("No hay Valoraciones");} %></h6>
                                
                                <form action="Descargar" method="POST">
                                    <div class="input-field">
                                        <button class="btn waves-effect waves-light cyan darken-1" type="submit" name="btnDownload">Descargar</button>
                                        <input type="hidden" id="objectId" name="objectId" value="<%= objectBlueprint.getIdBlueprint()%>">
                                        <input type="hidden" id="objectType" name="objectType" value="P">
                                    </div>
                                </form>
                            </div>
                            <%}%>
                            <%if(objType.contains("M")){%>
                            <span class="card-title col s12 m12"><%=objectMockup.getName()%></span>
                            <% List<Photo> listPhotos = photoService.GetPhotosByMockupId(objectMockup.getIdMockup());
                            %>
                            <div class="col s12 m12">
                                <h6 class="grey-text text-lighten-1">Descripción</h6>
                                <h6 class="grey-text"><%=objectMockup.getDescription()%></h6>
                                <h6 class="grey-text text-lighten-1">Valoración</h6>
                                <%int score = scoreService.GetPromObject("M", objectMockup.getIdMockup()); %>
                                <h6 class="grey-text"><%if(score>-1){ out.print(score);} else{out.print("No hay Valoraciones");} %></h6>
                                
                                <div class="carousel carousel-slider">
                                    <%for (Photo photo : listPhotos) {%>
                                    <a class="carousel-item"><img src="imageServlet?id=<%=photo.getIdPhoto()%>"></a>
                                    <%}%>
                                </div>
                                
                            </div>
                            <%}%>
                        </div>
                    </div>
                    <% if (!owner) {%>
                    <div class="card white">
                        <div class="card-content black-text row">
                            <span class="card-title col s12 m12">¡Valora este Aporte!</span>
                            <form action="Valorar" method="POST" class="row">
                                <div class="input-field col s12 m12">
                                    <p><input name="valueGroup" type="radio" id="0" value="0" />
                                        <label for="0">0</label></p>
                                    <p><input name="valueGroup" type="radio" id="1" value="1" />
                                        <label for="1">1</label></p>
                                    <p><input name="valueGroup" type="radio" id="2" value="2"/>
                                        <label for="2">2</label></p>
                                    <p><input name="valueGroup" type="radio" id="3" value="3"/>
                                        <label for="3">3</label></p>
                                    <p><input name="valueGroup" type="radio" id="4" value="4"/>
                                        <label for="4">4</label></p>
                                    <p><input name="valueGroup" type="radio" id="5" value="5"/>
                                        <label for="5">5</label></p>
                                    <%if (objType.contains("P")){%>
                                    <input type="hidden" id="objectId" name="objectId" value="<%= objectBlueprint.getIdBlueprint()%>">
                                    <input type="hidden" id="objectType" name="objectType" value="P">
                                    <% } %>
                                    <%if (objType.contains("M")){%>
                                    <input type="hidden" id="objectId" name="objectId" value="<%= objectMockup.getIdMockup() %>">
                                    <input type="hidden" id="objectType" name="objectType" value="M">
                                    <% } %>
                                </div>
                                <div class="input-field col s12 m12">
                                    <button class="btn waves-effect waves-light cyan darken-1" type="submit" name="btnValorar">Valorar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="card white">
                        <div class="card-content black-text row">
                            <span class="card-title col s12 m12">¡Envía un Comentario!</span>
                            <form action="Comentar" method="POST" class="row">
                                <div class="input-field col s8">
                                  <input placeholder="Ingrese su Comentario" id="commentInput" name="commentInput" type="text" class="validate" maxlength="140">
                                  <%if (objType.contains("P")){%>
                                    <input type="hidden" id="objectId" name="objectId" value="<%= objectBlueprint.getIdBlueprint()%>">
                                    <input type="hidden" id="objectType" name="objectType" value="P">
                                    <% } %>
                                    <%if (objType.contains("M")){%>
                                    <input type="hidden" id="objectId" name="objectId" value="<%= objectMockup.getIdMockup() %>">
                                    <input type="hidden" id="objectType" name="objectType" value="M">
                                    <% } %>
                                </div>
                                  <div class="input-field col s4">
                                      <button class="btn waves-effect waves-light cyan darken-1" type="submit" name="action">Enviar</button>
                                  </div>
                            </form>
                        </div>
                    </div>
                    <%}%>
                    <div class="col s12 m12">
                        <div class="card white">
                          <div class="card-content black-text">
                            <span class="card-title">Comentarios</span>
                          </div>
                        </div>
                    </div>
                    <%
                    for(Comment comment : comments)
                    {%>
                    <div class="col s6 m6">
                        <div class="card white">
                            <div class="card-content black-text row">
                                <div>
                                    <h6 class="black-text">
                                        <%= comment.getDescription() %>
                                    </h6>
                                    <h6 class="grey-text text-lighten-1">Comentario de: <%= comment.getUser().getName() %></h6>
                                </div>
                            </div>
                        </div>
                    </div><%}%>
            </div>
        </main>
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
