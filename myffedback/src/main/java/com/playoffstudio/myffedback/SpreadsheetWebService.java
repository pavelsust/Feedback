package com.playoffstudio.myffedback;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SpreadsheetWebService {

    @POST("1FAIpQLSeoszclnu_5IcbDTZOV676xMGvN9MrbGFML34Q_a_Ls5q7N_A/formResponse")
    @FormUrlEncoded
    Call<Void> feedbackSend(
            @Field("entry.1897273951") String feedback,
            @Field("entry.1216056379") String name,
            @Field("entry.1077014707") String email
    );

}
