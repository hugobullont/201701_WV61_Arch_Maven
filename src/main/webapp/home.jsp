<%-- 
    Document   : home
    Created on : 02-may-2017, 16:48:31
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
    String firstName = name.split(" ")[0];
    String imgurl = userService.GetProfilePictureUrlByFBId(uid);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>arch - Home</title>
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
                    <li><div class="userView">
                      <div class="background">
                        <img width="500px" src="resources/background2.jpg">
                      </div>
                      <img class="circle" src="<%out.println(imgurl);%>">
                      <span class="white-text name"><%out.println(name);%></span>
                      <span class="white-text email"></span>
                    </div></li>
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
                <div class="col s12 m6">
                  <div class="card cyan darken-2">
                      <div class="card-image">
                          <img src="resources/background3.jpg" height="300px">
                        <span class="card-title">Gusto de Verte, <%=firstName%> </span>
                      </div>
                    <div class="card-content white-text">
                      <p>Gracias por participar en la beta de arch. 
                          Al lado izquierdo encontrarás el menú con todas las opciones disponibles.</p>
                    </div>
                  </div>
                </div>
                
                <div class="col s12 m6">
                    <div class="card cyan darken-2">
                      <div class="card-image">
                        <img src="resources/background1.jpg" height="300px">
                        <span class="card-title">Compartir para Mejorar</span>
                      </div>
                      <div class="card-content white-text">
                        <p>En arch puedes compartir los archivos que desees para que sean comentados, criticados y puntuados por la comunidad.</p>
                      </div>
                    </div>
                  </div>
                
                <div class="col s12 m6">
                  <div class="card cyan darken-2">
                    <div class="card-content white-text">
                      <span class="card-title">Toda tu Información Protegida</span>
                      <p>Solo utilizamos Facebook para el inicio de sesión.
                      Los otros usuarios solo podrán acceder a lo que decidas publicar en arch.</p>
                    </div>
                  </div>
                </div>
                
                <div class="col s12 m6">
                  <div class="card cyan darken-2">
                    <div class="card-content white-text">
                      <span class="card-title">Constante Actualización</span>
                      <p>Siempre realizamos actualizaciones para arch.
                      No olvides revisar el menú para ver si hemos lanzado una nueva función.</p>
                    </div>
                  </div>
                </div>
            </div>
        </main>           
                      
                      
        <!--  Scripts-->
        <script src="js/materialize.js"></script>
        <script src="js/init.js"></script>
    </body>
</html>
