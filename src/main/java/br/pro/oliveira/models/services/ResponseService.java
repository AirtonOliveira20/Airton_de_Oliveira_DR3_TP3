package br.pro.oliveira.models.services;

import br.pro.oliveira.models.APIResposta;
import br.pro.oliveira.models.ApiAuxiliar;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;

public interface ResponseService {
    @GET("/posts")
        //TODOS
    Call<List<APIResposta>> list();

    @GET("/posts/{id}")
    Call<APIResposta> show(@Path("id") int id);

    @POST("/posts")
    Call<ApiAuxiliar> createPost(@Body ApiAuxiliar post);

}
