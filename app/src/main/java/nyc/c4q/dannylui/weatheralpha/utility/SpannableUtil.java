package nyc.c4q.dannylui.weatheralpha.utility;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;

import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

/**
 * Created by Danny on 10/27/2016.
 */

public class SpannableUtil {

    public static CharSequence changeTextSize(String text1, String text2){
        SpannableString span1 = new SpannableString(text1);
        span1.setSpan(new AbsoluteSizeSpan(100), 0, text1.length(), SPAN_INCLUSIVE_INCLUSIVE);

        SpannableString span2 = new SpannableString(text2);
        span2.setSpan(new AbsoluteSizeSpan(40), 0, text2.length(), SPAN_INCLUSIVE_INCLUSIVE);

        // let's put both spans together with a separator and all
        return TextUtils.concat(span1, " ", span2);

    }
}
