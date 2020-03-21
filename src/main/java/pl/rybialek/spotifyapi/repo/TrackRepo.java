package pl.rybialek.spotifyapi.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.rybialek.spotifyapi.entity.Track;

@Repository
public interface TrackRepo extends MongoRepository<Track, String> {

}
