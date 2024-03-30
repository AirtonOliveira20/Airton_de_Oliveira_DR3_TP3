package br.pro.oliveira;


import br.pro.oliveira.models.ApiAuxiliar;
import br.pro.oliveira.models.services.ResponseService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//Responsável por instanciar as interfaces de serviços da API
public class ApiEncapsulamento {
    private final Retrofit retrofit;

    private static ApiEncapsulamento instance = null;
    private ApiEncapsulamento(){
        String baseUrl = "https://jsonplaceholder.typicode.com/";
        this.retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    //Função pública para retornar uma instância criada
    private static ApiEncapsulamento getInstance(){
        if(instance == null){
            instance = new ApiEncapsulamento();
        }
        return instance;
    }

    public static ResponseService getResponseService(){
        return getInstance().retrofit.create(ResponseService.class);
    }
    public static Call<ApiAuxiliar> createPost(ApiAuxiliar novoPost) {
        return (Call<ApiAuxiliar>) getInstance().retrofit.create(ApiAuxiliar.class);
    }
}

