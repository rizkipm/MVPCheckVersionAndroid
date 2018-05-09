package superheroku.id.co.mvpandroidcheckandroidversion.views.maincheck;

import superheroku.id.co.mvpandroidcheckandroidversion.model.Data;
import superheroku.id.co.mvpandroidcheckandroidversion.views.base.ViewCheck;

public interface MainCheckView  extends ViewCheck{
    void onShowData(Data data);

    void checkAppVer();
    int getCurrentVersi();
    void getVersionInfo();
}
