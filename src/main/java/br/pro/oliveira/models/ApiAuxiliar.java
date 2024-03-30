package br.pro.oliveira.models;

import com.google.gson.annotations.SerializedName;

public class ApiAuxiliar {
        @SerializedName("id")
        public int id;
        @SerializedName("title")
        public String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @SerializedName("body")
        public String body;

        @SerializedName("userId")
        public int userId;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;

        }
}
