package androidwheelview.dusunboy.github.com.library.dialog;

/**
 * Created by Win8 on 2015/11/12.
 */
public interface OnDateSetListener extends OnSetListener{

    /**
     * 监听日期设置
     * @param wheelViewDialog
     * @param currentYear
     * @param currentMonth
     * @param currentDay
     * @param currentHour
     * @param currentMinutes
     */
    void onDateSet(WheelViewDialog wheelViewDialog, int currentYear, int currentMonth,
                   int currentDay, int currentHour, int currentMinutes);
}
