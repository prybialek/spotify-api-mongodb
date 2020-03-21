package pl.rybialek.spotifyapi.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.rybialek.spotifyapi.entity.Track;
import pl.rybialek.spotifyapi.model.SpotifyAlbum;
import pl.rybialek.spotifyapi.model.dto.SpotifyAlbumDTO;
import pl.rybialek.spotifyapi.repo.TrackRepo;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class SpotifyAlbumClient {

    private TrackRepo trackRepo;

    @Autowired
    public SpotifyAlbumClient(TrackRepo trackRepo) {
        this.trackRepo = trackRepo;
    }

    @GetMapping("/album/{authorName}")
    public Set<SpotifyAlbumDTO> getAlbumByAuthor(OAuth2Authentication details, @PathVariable String authorName) {
        String jwt = ((OAuth2AuthenticationDetails) details.getDetails()).getTokenValue();

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<SpotifyAlbum> exchange = restTemplate.exchange("https://api.spotify.com/v1/search?q=" + authorName + "&type=track&market=US&limit=10&offset=5",
                HttpMethod.GET,
                httpEntity,
                SpotifyAlbum.class);

        Set<SpotifyAlbumDTO> spotifyAlbums = Objects.requireNonNull(exchange.getBody())
                .getTracks()
                .getItems()
                .stream()
                .map(i -> new SpotifyAlbumDTO(i.getName(), i.getAlbum().getImages().get(0).getUrl()))
                .collect(Collectors.toSet());

        return spotifyAlbums;
    }

    @PostMapping("/add-track")
    public void addTrack(@RequestBody Track track) {
        trackRepo.save(track);
    }
}
