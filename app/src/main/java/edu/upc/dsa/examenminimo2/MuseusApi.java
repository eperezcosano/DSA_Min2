package edu.upc.dsa.examenminimo2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MuseusApi {

    @GET("dataset/museus/format/json/pag-ini/1/pag-fi/20")
    Call<Museums> getMuseums();

}
