package com.kevinj1008.taipeizooapisample.plantdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevinj1008.taipeizooapisample.R;
import com.kevinj1008.taipeizooapisample.model.Plant;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static androidx.core.util.Preconditions.checkNotNull;

public class PlantDetailFragment extends Fragment implements PlantDetailContract.View {

    private PlantDetailContract.Presenter mPresenter;

    private ImageView mPlantPicture;
    private TextView mNameCh;
    private TextView mNameEn;
    private TextView mPlantAlsoKnown;
    private TextView mPlantBrief;
    private TextView mFeature;
    private TextView mFunction;
    private TextView mUpdate;

    private static final String NAME_CH = "NameCh";
    private static final String NAME_EN = "NameEn";
    private static final String ALSO_KNOWN = "AlsoKnown";
    private static final String BRIEF = "Brief";
    private static final String FEATURE = "Feature";
    private static final String FUNCTION = "Function";
    private static final String UPDATE = "Update";

    public PlantDetailFragment() {
        // Requires empty public constructor
    }

    public static PlantDetailFragment newInstance() {
        return new PlantDetailFragment();
    }


    @Override
    public void setPresenter(PlantDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_plant_detail, container, false);
        mPlantPicture = root.findViewById(R.id.plant_detail_image);
        mNameCh = root.findViewById(R.id.plant_detail_name_ch);
        mNameEn = root.findViewById(R.id.plant_detail_name_en);
        mPlantAlsoKnown = root.findViewById(R.id.plant_detail_also_known);
        mPlantBrief = root.findViewById(R.id.plant_detail_brief);
        mFeature = root.findViewById(R.id.plant_detail_feaeture);
        mFunction = root.findViewById(R.id.plant_detail_function_application);
        mUpdate = root.findViewById(R.id.plant_detail_last_update);

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.start();
    }

    @Override
    public void showPlantDetailUi(Plant plant) {
        if (!"".equals(plant.getPicture01())) {
            Picasso.get()
                    .load(plant.getPicture01())
                    .placeholder(R.drawable.all_picture_placeholder)
                    .into(mPlantPicture);
        }
        mNameCh.setText(checkPlantString(NAME_CH, plant));
        mNameEn.setText(checkPlantString(NAME_EN, plant));
        mPlantAlsoKnown.setText(checkPlantString(ALSO_KNOWN, plant));
        mPlantBrief.setText(checkPlantString(BRIEF, plant));
        mFeature.setText(checkPlantString(FEATURE, plant));
        mFunction.setText(checkPlantString(FUNCTION, plant));
        mUpdate.setText(checkPlantString(UPDATE, plant));
    }

    private CharSequence checkPlantString(String text, Plant plant) {
        switch (text) {
            case NAME_CH :
                if (!"".equals(plant.getNameCh())) return plant.getNameCh();
                else return getString(R.string.no_plant_detail_name_ch);

            case NAME_EN :
                if (!"".equals(plant.getNameEn())) return plant.getNameEn();
                else return getString(R.string.no_plant_detail_name_en);

            case ALSO_KNOWN :
                if (!"".equals(plant.getAlsoKnown())) return plant.getAlsoKnown();
                else return getString(R.string.no_plant_detail_also_known);

            case BRIEF:
                if (!"".equals(plant.getBrief())) return plant.getBrief();
                else return getString(R.string.no_plant_detail_brief);

            case FEATURE :
                if (!"".equals(plant.getFeature())) return plant.getFeature();
                else return getString(R.string.no_plant_detail_feature);

            case FUNCTION :
                if (!"".equals(plant.getFunctionApplication())) return plant.getFunctionApplication();
                else return getString(R.string.no_plant_detail_fuction);

            case UPDATE :
                if (!"".equals(plant.getUpdate())) return plant.getUpdate();
                else return getString(R.string.no_plant_detail_update);

            default:
                return getString(R.string.no_plant_detail_info);
        }
    }

    @Override
    public void refreshUi() {

    }

}
