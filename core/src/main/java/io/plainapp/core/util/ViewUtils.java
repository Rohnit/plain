package io.plainapp.core.util;


import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Outline;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewOutlineProvider;

import androidx.annotation.NonNull;

/**
 * Utility methods for working with Views.
 */
public class ViewUtils {

  private ViewUtils() {
  }




  /**
   * Determine if the navigation bar will be on the bottom of the screen, based on logic in
   * PhoneWindowManager.
   */
  public static boolean isNavBarOnBottom(@NonNull Context context) {
    final Resources res= context.getResources();
    final Configuration cfg = context.getResources().getConfiguration();
    final DisplayMetrics dm =res.getDisplayMetrics();
    boolean canMove = (dm.widthPixels != dm.heightPixels &&
        cfg.smallestScreenWidthDp < 600);
    return(!canMove || dm.widthPixels < dm.heightPixels);
  }


  public static final ViewOutlineProvider CIRCULAR_OUTLINE = new ViewOutlineProvider() {
    @Override
    public void getOutline(View view, Outline outline) {
      outline.setOval(view.getPaddingLeft(),
          view.getPaddingTop(),
          view.getWidth() - view.getPaddingRight(),
          view.getHeight() - view.getPaddingBottom());
    }
  };


}
