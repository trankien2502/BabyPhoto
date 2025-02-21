package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.ads.IsNetWork;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ApiDataService;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data.ConstantApiData;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconDatabase;
import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;

import java.util.ArrayList;
import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.util.SPUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApiUtils {

    public static void callDataApi(Context context) {
        if (SPUtils.getBoolean(context, SPUtils.CALL_API_SUCCESS, false)) return;
        if (IsNetWork.haveNetworkConnection(context)) {
            try {
                List<IconModel> stringList = new ArrayList<>();
                IconDatabase.getInstance(context).iconDAO().deleteAll();
                String fixedKey = "eMiyPtgg5B2Xyd5VrGjso8Zd3Iy4D";
                ApiDataService.apiService.callDataApi(fixedKey).enqueue(new Callback<List<IconModel>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<IconModel>> call, @NonNull Response<List<IconModel>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            stringList.addAll(response.body());
                            Log.e("call_api_data", "call:" + stringList.toString());
                            for (int i = 0; i < stringList.size(); i++) {
                                IconDatabase.getInstance(context).iconDAO().insert(stringList.get(i));
                                if (i == stringList.size() - 1 && stringList.size() == 627) {
                                    SPUtils.setBoolean(context, SPUtils.CALL_API_SUCCESS, true);
                                    SPUtils.setLong(context, SPUtils.CALL_API_TIME_DONE, System.currentTimeMillis());
                                    Log.e("call_api_data", "call success");
                                }
                            }
                        } else {
                            Log.e("call_api_data", "call false: Code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<IconModel>> call, @NonNull Throwable t) {
                        Log.e("call_api_data", "onfailure" + t);
                    }
                });
            } catch (Exception e) {
                Log.e("call_api_data", "catch: ", e);
            }
        } else {
            Log.e("call_api_data", "No internet to call api");
        }
    }


    public static void callApi(Context context) {
        SPUtils.setLong(context, SPUtils.CALL_API_TIME_DONE, 0);
        SPUtils.setBoolean(context, SPUtils.CALL_API_SUCCESS, false);
        callDataApi(context);
    }
}
