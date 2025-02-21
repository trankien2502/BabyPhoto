package picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.api_data;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import picturesofnewborns.picturesfornewborns.babyphoto.monthlymilestone.maternityphoto.database.icon.IconModel;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiDataService {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    ApiDataService apiService = new Retrofit.Builder()
            .baseUrl("https://haiyan116.net/babyphoto3/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiDataService.class);

    @GET("getdata.php")
    Call<List<IconModel>> callDataApi(@Query("key") String key);

}
