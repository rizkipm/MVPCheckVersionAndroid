package superheroku.id.co.mvpandroidcheckandroidversion.views.base;

public interface PresenterCheck <T extends ViewCheck> {
    void onAttach(T view);

    void onDetach();

}
