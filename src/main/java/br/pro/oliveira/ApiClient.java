package br.pro.oliveira;


import br.pro.oliveira.models.JsonUni;
import br.pro.oliveira.models.services.ResponseService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Responsável por instanciar as interfaces de serviços da API
public class ApiClient {
    private final Retrofit retrofit;

    private static ApiClient instance = null;
    private ApiClient(){
        String baseUrl = "https://jsonplaceholder.typicode.com/";
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Função pública para retornar uma instância criada
    private static ApiClient getInstance(){
        if(instance == null){
            instance = new ApiClient();
        }
        return instance;
    }

    public static ResponseService getResponseService(){
        return getInstance().retrofit.create(ResponseService.class);
    }
    public static Call<JsonUni> createPost(JsonUni novoPost) {
        return (Call<JsonUni>) getInstance().retrofit.create(JsonUni.class);
    }
}

