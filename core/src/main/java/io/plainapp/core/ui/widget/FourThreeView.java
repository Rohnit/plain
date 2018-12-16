package io.plainapp.core.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * A View that always has a 4:3 aspect ratio.
 */
public class FourThreeView extends View {


  public FourThreeView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  protected void onMeasure(int widthSpec, int heightSpec) {
    int fourThreeHeight = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthSpec) * 3 / 4,
        MeasureSpec.EXACTLY);
    super.onMeasure(widthSpec, fourThreeHeight);
  }

  @Override
  public boolean hasOverlappingRendering() {
    return false;
  }
}
