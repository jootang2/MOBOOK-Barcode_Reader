package com.launcher.mobookjfx;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 예시 메인 화면 컨트롤러 클래스
public class MainController {
    @FXML
    private Label welcomeLabel;
    @FXML
    private TextField isbn;
    @FXML
    private Text title;
    @FXML
    private Text link;
    @FXML
    private Text author;
    @FXML
    private Text img;
    @FXML
    private Text publisher;
    @FXML
    private Text pubdate;

    public void initData(String username) {
        welcomeLabel.setText("환영합니다, " + username + "님!");
    }
    @FXML
    public void search(ActionEvent event){
        String input = isbn.getText();

        BookData responseData = naverSearch(input);
        title.setText(responseData.getTitle());
        link.setText(responseData.getLink());
        author.setText(responseData.getAuthor());
        img.setText(responseData.getImg());
        publisher.setText(responseData.getPublisher());
        pubdate.setText(responseData.getPubdate());
    }

    public BookData naverSearch(String isbn){
        BookData bookData = new BookData();
        try{
            String url = "https://openapi.naver.com/v1/search/book_adv?d_isbn=" + isbn ;
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-Naver-Client-Id", AppConfig.getSensitiveData1());
            connection.setRequestProperty("X-Naver-Client-Secret", AppConfig.getSensitiveData2());

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();

                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode rootNode = objectMapper.readTree(content.toString());

                String title = rootNode.path("items").get(0).path("title").asText();
                String link = rootNode.path("items").get(0).path("link").asText();
                String author = rootNode.path("items").get(0).path("author").asText();
                String img = rootNode.path("items").get(0).path("img").asText();
                String publisher = rootNode.path("items").get(0).path("publisher").asText();
                String pubdate = rootNode.path("items").get(0).path("pubdate").asText();

                bookData.setTitle(title);
                bookData.setLink(link);
                bookData.setAuthor(author);
                bookData.setImg(img);
                bookData.setPublisher(publisher);
                bookData.setPubdate(pubdate);


                return bookData;
            } else {
                return bookData;

            }

        } catch(Exception e){
            e.printStackTrace();
            return bookData;
        }
    }
}
