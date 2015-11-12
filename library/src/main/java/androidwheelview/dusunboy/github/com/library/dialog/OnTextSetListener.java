package androidwheelview.dusunboy.github.com.library.dialog;

/**
 * Created by Win8 on 2015/11/12.
 */
public interface OnTextSetListener extends OnSetListener{

    /**
     * 监听文本设置
     * @param currentFirstStr
     * @param currentThirdStr
     * @param currentFourthStr
     * @param currentFifthStr
     * @param wheelViewDialog
     * @param text
     */
    void onTextSet(WheelViewDialog wheelViewDialog, String text);
}
