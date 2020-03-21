package pl.rybialek.spotifyapi.model.dto;

public class SpotifyAlbumDTO {

    private String trackName;
    private String imageUrl;

    public SpotifyAlbumDTO() {
    }

    public SpotifyAlbumDTO(String trackName, String imageUrl) {
        this.trackName = trackName;
        this.imageUrl = imageUrl;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
