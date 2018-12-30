package com.crud.tasks.trello.client;

import com.crud.tasks.domain.TrelloBoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TrelloClient {

    @Value("${trello.api.endpoint.prod}")
    private String trelloApiEndpoint;
    @Value("${trello.app.key}")
    private String trelloAppKey;
    @Value("${trello.app.token}")
    private String trelloToken;
    @Value("${trello.app.username}")
    private String trelloUsername;
    @Autowired
    private RestTemplate restTemplate;

    private URI buildUrl(){
        URI url = UriComponentsBuilder.fromHttpUrl(trelloApiEndpoint + "/members/" + trelloUsername + "/boards")
                .queryParam("key", trelloAppKey)
                .queryParam("token", trelloToken)
                .queryParam("fields", "name,id").build().encode().toUri();
        return url;
    }
    public List<TrelloBoardDto> getTrelloBoards(){
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(this.buildUrl(), TrelloBoardDto[].class);
        Optional<TrelloBoardDto[]> response = Optional.ofNullable(boardsResponse);
        TrelloBoardDto[] newResponse = response.orElse(new TrelloBoardDto[]{});
        return Arrays.asList(newResponse).stream()
                .filter(b -> !(b.getId().equals(null)))
                .filter(b -> b.getName().contains("Kodilla"))
                .collect(Collectors.toList());
    }
}
