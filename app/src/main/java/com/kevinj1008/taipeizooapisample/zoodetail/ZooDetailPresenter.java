package com.kevinj1008.taipeizooapisample.zoodetail;

import android.util.Log;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.TaipeiZoo;
import com.kevinj1008.taipeizooapisample.api.ApiConstants;
import com.kevinj1008.taipeizooapisample.api.ApiHelper;
import com.kevinj1008.taipeizooapisample.api.ApiService;
import com.kevinj1008.taipeizooapisample.api.bean.GetPlants;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.kevinj1008.taipeizooapisample.model.Zoo;
import com.kevinj1008.taipeizooapisample.model.response.PlantResponse;
import com.kevinj1008.taipeizooapisample.model.result.PlantResult;
import com.kevinj1008.taipeizooapisample.repositry.PlantRepository;
import com.kevinj1008.taipeizooapisample.util.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

import static androidx.core.util.Preconditions.checkNotNull;

public class ZooDetailPresenter implements ZooDetailContract.Presenter {

    private ZooDetailContract.View mZooDetailView;
    private Zoo mZoo;
    private GetPlants mPlants = new GetPlants();

    public ZooDetailPresenter(ZooDetailContract.View zooDetailView, Zoo zoo) {
        mZooDetailView = checkNotNull(zooDetailView, "zooDetailView cannot be null!");
        this.mZoo = zoo;
        mZooDetailView.setPresenter(this);
    }

    @Override
    public void start() {
        showZoo(mZoo);
        loadPlants();
    }

    @Override
    public void loadPlants() {
        PlantRepository.getPlant()
                .doOnSubscribe(disposable -> mZooDetailView.showZooProgressBar(true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<PlantResponse>() {
                    @Override
                    public void onSuccess(PlantResponse plantResponse) {
                        PlantResult plantResult = plantResponse.getPlantResult();
                        for (int i = 0; i < plantResult.getPlants().size(); i++) {
                            String location = plantResult.getPlants().get(i).getLocation();
                            if (null != location && location.contains(mZoo.getName().substring(0, 2))
                                    || TaipeiZoo.getAppContext().getResources()
                                    .getString(R.string.location_everywhere_string).equals(location)) {
                                Plant plant = plantResult.getPlants().get(i);
                                mPlants.getPlants().add(plant);
                            }
                        }
                        mZooDetailView.showZooProgressBar(false);
                        showPlants(mPlants);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(Constants.TAG, "Load Plants error: " + e.getMessage());
                    }
                });
    }

    @Override
    public void showPlants(GetPlants plants) {
        mZooDetailView.showPlants(plants);
    }

    @Override
    public void showZoo(Zoo zoo) {
        mZooDetailView.showZooDetailUi(mZoo);
    }

    @Override
    public void openPlantDetail(Plant plant) {
        mZooDetailView.showPlantDetailUi(plant);
    }

    @Override
    public void openZooWeb(String url) {
        mZooDetailView.showZooWeb(url);
    }

    @Override
    public void refresh() {
        mZooDetailView.refreshUi();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

}
