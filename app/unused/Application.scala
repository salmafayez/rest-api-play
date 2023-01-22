package controllers

import com.mohiva.play.silhouette.api.services.IdentityService
import com.mohiva.play.silhouette.api.{Env, HandlerResult, LoginInfo, Silhouette}
import com.mohiva.play.silhouette.impl.authenticators.JWTAuthenticator
import play.api.libs.json.Json
import play.api.mvc.Results.{Forbidden, Ok, Unauthorized}
import play.mvc.Controller
import unused.User
import unused.dtos.UserDto

import javax.inject.{Inject, Singleton}

trait JWTEnv extends Env {
  type I = User
  type A = JWTAuthenticator
}

@Singleton
class Application@Inject()(silhouette: Silhouette[JWTEnv]) extends Controller{

  def index = silhouette.SecuredAction { implicit request =>
    Ok("")
  }

  def signIn = silhouette.UnsecuredAction { implicit request =>
    Ok("")
  }
}