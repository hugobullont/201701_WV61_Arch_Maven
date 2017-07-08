<%-- 
    Document   : addObject
    Created on : 17-jun-2017, 13:51:29
    Author     : Hugo
--%>

<%@page import="BusinessLogic.Users.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    HttpSession httpsession = request.getSession(false);
    IUserService userService = new UserService();
    String uid = (String) httpsession.getAttribute("uid");
    String accessToken = (String) httpsession.getAttribute("accessToken");
    String name = (String) httpsession.getAttribute("name");
    String imgurl = userService.GetProfilePictureUrlByFBId(uid);
    String actionAdd = (String) httpsession.getAttribute("actionAdd");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>arch - Agregar <%=actionAdd%> </title>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
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
        <main>
            <div class="row">
                <div class="col s12 m12">
                  <div class="card white">
                    <div class="card-content black-text">
                      <span class="card-title">Agregar <%=actionAdd%></span>
                      <form action="Agregar_<%=actionAdd%>" method="POST" enctype="multipart/form-data" name="Agregar_<%=actionAdd%>" >
                          <div class="row">
                            <div class="input-field col s12">
                                <input id="txtname<%=actionAdd%>" name="txtname<%=actionAdd%>" type="text" class="validate" maxlength="45" data-length="45" required>
                                <label for="txtname<%=actionAdd%>">Nombre (<%=actionAdd%>)</label>
                            </div>
                            <div class="input-field col s12">
                                <textarea id="txtAreaDescription<%=actionAdd%>"  name="txtAreaDescription<%=actionAdd%>" class="materialize-textarea" maxlength="255" data-length="255" required></textarea>
                                <label for="txtAreaDescription<%=actionAdd%>">Descripción</label>
                            </div>
                            <%if (actionAdd == "Plano") {%>
                            <div class="file-field input-field col s12">
                                <div class="btn cyan darken-1">
                                  <span>Examinar</span>
                                  <input id="fileBlueprints" type="file" name="fileBlueprints" accept=".dwg, .zip, .rar" required>
                                </div>
                                <div class="file-path-wrapper">
                                  <input class="file-path validate" type="text" placeholder="Sube tu Plano en formato DWG, ZIP o RAR - 16MB Máx.">
                                </div>
                            </div>
                            <%}%>
                            <%if (actionAdd == "Maqueta") {%>
                            <div class="file-field input-field col s12">
                                <div class="btn cyan darken-1">
                                  <span>Examinar</span>
                                  <input multiple id="filePhotos" type="file" name="filePhotos" accept="image/*" required>
                                </div>
                                <div class="file-path-wrapper">
                                  <input class="file-path validate" type="text" placeholder="Sube una o más fotos de tu Maqueta - 16MB Máx por Foto">
                                </div>
                            </div>
                            <%}%>
                            <div class="input-field col s12">
                                <button class="btn waves-effect waves-light cyan darken-1" type="submit" name="action">Subir
                                    <i class="material-icons right">send</i>
                                </button>
                            </div> 
                          </div>
                      </form>
                    </div>
                  </div>
                </div>
            </div>
        </main>
        
                      
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
