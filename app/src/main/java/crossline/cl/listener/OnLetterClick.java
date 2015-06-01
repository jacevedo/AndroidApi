package crossline.cl.listener;

import crossline.cl.views.CustomAlphabetical;

/**
 * Created by jacevedo on 03-01-15.
 */
public interface OnLetterClick
{
    public void onLetterClickListener(CustomAlphabetical view, char letterClick, boolean isEnabled);
}