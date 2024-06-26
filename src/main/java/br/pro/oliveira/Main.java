package br.pro.oliveira;

import br.pro.oliveira.models.APIResposta;
import br.pro.oliveira.models.ApiAuxiliar;
import br.pro.oliveira.models.services.ResponseService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Main
{
    public static Scanner scanner = new Scanner(System.in);
    public static void main( String[] args ) throws IOException {
        exibirMenu();

    }

    public static void exibirMenu() throws IOException {

        Scanner scanner = new Scanner(System.in);
        boolean sair = false;
        do {
            System.out.println("===== Menu =====");
            System.out.println("1. Exibir Lista");
            System.out.println("2. Exibir Item");
            System.out.println("3. Inserir Item");
            System.out.println("4. Exibir tarefas concluidas");
            System.out.println("5. Marcar tarefa como concluida");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    exibirLista();
                    break;
                case 2:
                    exibirItem();
                    break;
                case 3:
                    inserirItem();
                    break;
                case 4:
                    exibirTarefasConcluidas();
                    break;

                case 5:
                    marcarTarefaConcluida();
                    break;

                case 0:
                    sair = true;
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (!sair);
    }

    public static void exibirLista(){
        try {
            Call<List<APIResposta>> call = ApiEncapsulamento.getResponseService().list();

            Response<List<APIResposta>> resp = call.execute();

            List<APIResposta> apiResposta = resp.body();

            System.out.println("Exibir Lista: ");

            for (APIResposta aux : apiResposta) {
                exibirItemDetalhado(aux);

            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void exibirItem() {
        try {

            System.out.println("Qual item deseja exibir?:");
            int id = scanner.nextInt();

            Call<APIResposta> call = ApiEncapsulamento
                    .getResponseService()
                    .show(id);

            Response<APIResposta> resp = call.execute();

            APIResposta apiResposta = resp.body();

            System.out.println("Exibir um item: \n");
            System.out.println("ID: " + apiResposta.id);
            System.out.println("UserID: " + apiResposta.userid);
            System.out.println("Title: " + apiResposta.title);
            System.out.println("Completed: " + apiResposta.completed);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inserirItem(){
        ResponseService respostasService = ApiEncapsulamento.getResponseService();
        ApiAuxiliar novoItem = new ApiAuxiliar();

        System.out.println("Preencha os dados do novo item:");
        System.out.print("ID: ");
        novoItem.setId(scanner.nextInt());
        System.out.print("Title: ");
        novoItem.setTitle(scanner.next());
        System.out.print("Body: ");
        novoItem.setBody(scanner.next());
        System.out.print("UserId: ");
        novoItem.setUserId(scanner.nextInt());

        Call<ApiAuxiliar> call = respostasService.createPost(novoItem);
        call.enqueue(new Callback<ApiAuxiliar>() {
            @Override
            public void onResponse(Call<ApiAuxiliar> call, Response<ApiAuxiliar> response) {
                if (response.isSuccessful()) {
                    ApiAuxiliar apiAuxiliar = response.body();
                    System.out.println("Item inserido:");

                    exibirItemDetalhado(apiAuxiliar);


                } else {
                    System.out.println("Erro de requisição");
                }
            }

            @Override
            public void onFailure(Call<ApiAuxiliar> call, Throwable throwable) {

                System.err.println("Erro na requisição: " + throwable.getMessage());

            }

       });
   }

   public static void marcarTarefaConcluida(){
       try {
           Call<APIResposta> call = ApiEncapsulamento.getResponseService().show(1);
           Response<APIResposta> resp = call.execute();

           if (resp.isSuccessful()) {
               APIResposta tarefa = resp.body();
               tarefa.completed = true;

               exibirItemDetalhado(tarefa);

           } else {
               System.out.println("Erro ao obter detalhes da tarefa.");
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }


    public static void exibirTarefasConcluidas() throws IOException {
       System.out.println("1. Filtrar por Tarefas Concluídas");

       try {
           Call<List<APIResposta>> call = ApiEncapsulamento.getResponseService().list();
           Response<List<APIResposta>> resp = call.execute();

           if (resp.isSuccessful()) {
               List<APIResposta> listaTarefas = resp.body();
               System.out.println("Tarefas Concluídas: ");
               for (APIResposta tarefa : listaTarefas) {
                   if (tarefa.completed == true ) {
                       exibirItemDetalhado(tarefa);
                   }
               }
           } else {
               System.out.println("Erro ao obter lista de tarefas.");
           }
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }

    public static void exibirItemDetalhado(APIResposta item) {
        System.out.println("ID: " + item.id);
        System.out.println("UserID: " + item.userid);
        System.out.println("Title: " + item.title);
        System.out.println("Completed: " + item.completed);
        System.out.println("-----------------------");
    }

    public static void exibirItemDetalhado(ApiAuxiliar item) {
        System.out.println("ID: " + item.id);
        System.out.println("Title: " + item.title);
        System.out.println("Body: " + item.body);
        System.out.println("UserId: " + item.userId);
        System.out.println("-----------------------");
    }
}


