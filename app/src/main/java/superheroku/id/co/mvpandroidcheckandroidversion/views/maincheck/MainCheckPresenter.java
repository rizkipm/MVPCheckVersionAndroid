package superheroku.id.co.mvpandroidcheckandroidversion.views.maincheck;

import superheroku.id.co.mvpandroidcheckandroidversion.model.Data;
import superheroku.id.co.mvpandroidcheckandroidversion.views.base.PresenterCheck;

public class MainCheckPresenter implements PresenterCheck<MainCheckView> {

    private MainCheckView mView;

    @Override
    public void onAttach(MainCheckView view) {
        mView = view;

    }

    @Override
    public void onDetach() {
        mView = null;

    }

    public void showData() {


        // Set Data
        final Data data = new Data();
        data.setTextHasilVersion("Hello from Data!");

        // Show Fragment with Data
        mView.onShowData(data);
    }
}
