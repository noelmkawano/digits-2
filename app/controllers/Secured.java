package controllers;

import models.ContactDB;
import models.User;
import play.mvc.Result;
import play.mvc.Http.Context;
import play.mvc.Security;


/**
 * Implement authorization for the system.
 * getUserName() and onAuthorized override superclass methods to restrict access to the NewContact page to an
 * associated user only.
 *
 * getUser(), isLoggedIn, and GetUserInfo() provide static helper methods so that controllers can know when a
 * user is logged in.
 *
 * Based on original implementation by Philip Johnson at
 */
public class Secured extends Security.Authenticator {

  /**
   * Used by the authentication annotation to determine if the user is looged in.
   *
   * @param context The context.
   * @return The email address of a logged in user, or null if not logged in.
   */
  @Override
  public String getUsername(Context context) {
    return context.session().get("email");
  }

  /**
   * Instruct authenticator to automatically redirect to the home page if unauthorized.
   *
   * @param context The context.
   * @return The home page.
   */
  @Override
  public Result onUnauthorized(Context context) {
    return redirect(routes.Application.index());
  }

  /**
   * Return the email address of the logged in user.
   *
   * @param context The context.
   * @return The email address of the logged in user.
   */
  public static String getUser(Context context) {
    return context.session().get("email");
  }

  /**
   * Check if the user is loged in.
   * @param context The context.
   * @return True if the user is logged in, null otherwise.
   */
  public static boolean isLoggedIn(Context context) {
    return (getUser(context) != null);
  }

  /**
   * Return the User Info of the logged in user, or null if no user is logged in.
   *
   * @param context The context.
   * @return The User object, or null.
   */
  public static User getUserInfo(Context context) {
    return (isLoggedIn(context) ? ContactDB.getUser(getUser(context)) : null);
  }
}
